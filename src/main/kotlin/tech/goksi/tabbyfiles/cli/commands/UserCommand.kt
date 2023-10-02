package tech.goksi.tabbyfiles.cli.commands

import jakarta.validation.Validator
import org.jline.terminal.Terminal
import org.springframework.shell.component.flow.ComponentFlow
import org.springframework.shell.component.flow.SelectItem
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.table.BeanListTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder
import tech.goksi.tabbyfiles.requests.UserRequest
import tech.goksi.tabbyfiles.services.RoleService
import tech.goksi.tabbyfiles.services.TabbyUserService

@ShellComponent
@ShellCommandGroup("User Commands")
class UserCommand(
    private val userService: TabbyUserService,
    private val roleService: RoleService,
    private val terminal: Terminal,
    private val validator: Validator,
    private val flowBuilder: ComponentFlow.Builder
) {
    @ShellMethod(key = ["user-list"], value = "Shows list of all currently registered users")
    fun list() {
        val users = userService.getAllUsers()
        val tableModel =
            BeanListTableModel(
                users,
                linkedMapOf("id" to "ID", "username" to "Username", "roles" to "Roles", "createdAt" to "Registered at")
            )
        val tableBuilder = TableBuilder(tableModel)
        tableBuilder.addFullBorder(BorderStyle.fancy_light)
        terminal.writer().println(tableBuilder.build().render(80))
    }

    @ShellMethod(key = ["user-register"], value = "Register new user on the system")
    fun register() {
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
            try {
                val user = userService.addUser(userRequest)
                terminal.writer()
                    .println("New user with username \"$username\" and id ${user.id} is successfully added to the system !")
            } catch (exception: Exception) {
                terminal.writer().println("Error while adding new user !") // TODO
            }
        } else {
            for (constraintViolation in failed) {
                terminal.writer().println(constraintViolation.message)
            }
        }
    }

}