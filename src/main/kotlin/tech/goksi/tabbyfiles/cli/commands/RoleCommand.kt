package tech.goksi.tabbyfiles.cli.commands

import jakarta.validation.Validator
import org.springframework.shell.component.ConfirmationInput
import org.springframework.shell.component.flow.ComponentFlow
import org.springframework.shell.standard.*
import org.springframework.web.server.ResponseStatusException
import tech.goksi.tabbyfiles.cli.services.ConsoleServices
import tech.goksi.tabbyfiles.cli.services.TableServices
import tech.goksi.tabbyfiles.requests.RoleRequest
import tech.goksi.tabbyfiles.services.RoleService

@ShellComponent
@ShellCommandGroup("Role Commands")
class RoleCommand(
    private val roleService: RoleService,
    private val tableServices: TableServices,
    private val flowBuilder: ComponentFlow.Builder,
    private val validator: Validator,
    private val console: ConsoleServices
) : AbstractShellComponent() {
    @ShellMethod(key = ["role-list"], value = "Shows list of all roles existing on the system")
    fun list() {
        val roles = roleService.getAllRoles()
        tableServices.printTable(
            roles,
            linkedMapOf(
                "id" to "ID",
                "name" to "Name",
                "color" to "Color", // TODO: don't like this
                "admin" to "Admin",
                "maxUpload" to "Upload limit",
                "admin" to "Admin"
            )
        )
    }

    @ShellMethod(key = ["role-create"], value = "Creates new role on the system")
    fun create() {
        val flow = flowBuilder.clone().reset()
            .withStringInput("name")
            .name("Role name: ")
            .and()
            .withStringInput("color")
            .name("Enter role color hex code (ex. FF0000 for red): ")
            .and()
            .withConfirmationInput("admin")
            .name("Should role be admin ?")
            .defaultValue(false)
            .and()
            .withStringInput("maxUpload")
            .name("Maximum upload limit (in MB): ")
            .defaultValue("-1")
            .and().build()

        val result = flow.run()
        val resultContext = result.context

        val name = resultContext.get<String>("name")
        val color = resultContext.get<String>("color")
        val admin = resultContext.get<Boolean>("admin")
        val maxUpload = resultContext.get<String>("maxUpload").toLongOrNull() ?: -2

        val roleRequest = RoleRequest(name, color, admin, maxUpload)
        val failed = validator.validate(roleRequest)

        if (failed.isEmpty()) {
            try {
                val role = roleService.addRole(roleRequest)
                console.success("Successfully added new role to system with name %s and id %d !", role.name, role.id)
            } catch (exception: Exception) {
                console.error("Error while adding new role !") // TODO
                throw exception
            }
        } else {
            for (constraintViolation in failed) {
                console.error(constraintViolation.message)
            }
        }
    }

    @ShellMethod(key = ["role-delete"], value = "Deletes specific role from the system !")
    fun delete(@ShellOption(help = "ID of role u wish to remove from system") id: Long) {
        val role = try {
            roleService.getRoleById(id)
        } catch (exception: ResponseStatusException) {
            if (exception.statusCode.value() == 404) {
                console.error("Role with id %d is not found in the system !", id)
                null
            } else throw exception
        } ?: return

        val confirmation =
            ConfirmationInput(terminal, "Are you sure that you want to delete user ${role.name} ?", false)
        confirmation.setResourceLoader(resourceLoader)
        confirmation.templateExecutor = templateExecutor
        val resultContext = confirmation.run(ConfirmationInput.ConfirmationInputContext.empty())

        if (resultContext.resultValue) {
            roleService.deleteRole(role)
            console.success("Role with name %s is successfully deleted from the system !", role.name)
        } else {
            console.write("Aborting...")
        }
    }
}