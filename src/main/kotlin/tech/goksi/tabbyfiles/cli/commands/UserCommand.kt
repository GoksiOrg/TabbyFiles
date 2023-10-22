package tech.goksi.tabbyfiles.cli.commands

import jakarta.validation.Validator
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.shell.component.ConfirmationInput
import org.springframework.shell.component.ConfirmationInput.ConfirmationInputContext
import org.springframework.shell.component.flow.ComponentFlow
import org.springframework.shell.component.flow.SelectItem
import org.springframework.shell.standard.*
import org.springframework.web.server.ResponseStatusException
import tech.goksi.tabbyfiles.cli.services.ConsoleServices
import tech.goksi.tabbyfiles.cli.services.TableServices
import tech.goksi.tabbyfiles.requests.UserRequest
import tech.goksi.tabbyfiles.services.RoleService
import tech.goksi.tabbyfiles.services.TabbyUserService

@ShellComponent
@ShellCommandGroup("User Commands")
class UserCommand(
    private val userService: TabbyUserService,
    private val roleService: RoleService,
    private val console: ConsoleServices,
    private val validator: Validator,
    private val flowBuilder: ComponentFlow.Builder,
    private val tableServices: TableServices
) : AbstractShellComponent() {
    @ShellMethod(key = ["user-list"], value = "Shows list of all currently registered users")
    fun list() {
        val users = userService.getAllUsers()
        tableServices.printTable(
            users,
            linkedMapOf("id" to "ID", "username" to "Username", "roles" to "Roles", "createdAt" to "Registered at")
        )
    }

    @ShellMethod(key = ["user-create"], value = "Register new user on the system")
    fun create() {
        val roles = roleService.getAllRoles()
        val rolesToSelect = roles.map { SelectItem.of(it.name, it.name) }
        val flow = flowBuilder.clone().reset()
            .withStringInput("username")
            .name("Username: ")
            .and()
            .withStringInput("password")
            .name("Password: ")
            .maskCharacter('*')
            .and()
            .withMultiItemSelector("roles")
            .name("Roles")
            .selectItems(rolesToSelect)
            .and()
            .build()
        val result = flow.run()
        val resultContext = result.context

        val username = resultContext.get<String>("username")
        val password = resultContext.get<String>("password")
        val rolesToGive = resultContext.get<List<String>>("roles").map { str -> roles.find { it.name == str }!! }

        val userRequest = UserRequest(username, password, rolesToGive)
        val failed = validator.validate(userRequest)
        if (failed.isEmpty()) {
            val user = userService.addUser(userRequest) //TODO: handle same name, same for role
            console.success(
                "New user with username %s and id %d is successfully added to the system !",
                username,
                user.id
            )
        } else {
            for (constraintViolation in failed) {
                console.error(constraintViolation.message)
            }
        }
    }

    @ShellMethod(key = ["user-info"], value = "Display specific user info")
    fun info(@ShellOption(help = "Username of the user", arity = 1) username: String) {
        val user = try {
            userService.getUserByUsername(username)
        } catch (exception: UsernameNotFoundException) {
            console.error("User with username %s is not found in the system !", username)
            null
        } ?: return
        tableServices.printTable(
            user,
            linkedMapOf("id" to "ID", "username" to "Username", "roles" to "Roles", "createdAt" to "Registered at")
        )
    }

    @ShellMethod(key = ["user-delete"], value = "Delete specific user from the system")
    fun delete(@ShellOption(help = "ID of user u wish to remove from system") id: Long) {
        val user = try {
            userService.getUserById(id)
        } catch (exception: ResponseStatusException) {
            if (exception.statusCode.value() == 404) {
                console.error("User with id %d is not found in the system !", id)
                null
            } else throw exception
        } ?: return
        val confirmation =
            ConfirmationInput(terminal, "Are you sure that you want to delete user ${user.username} ?", false)
        confirmation.setResourceLoader(resourceLoader)
        confirmation.templateExecutor = templateExecutor
        val resultContext = confirmation.run(ConfirmationInputContext.empty())

        if (resultContext.resultValue) {
            userService.deleteUser(user)
            console.success("User with username %s is successfully deleted from the system !", user.username)
        } else {
            console.write("Aborting...")
        }
    }
}