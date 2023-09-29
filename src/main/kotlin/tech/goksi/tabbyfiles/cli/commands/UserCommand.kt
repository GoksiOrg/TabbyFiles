package tech.goksi.tabbyfiles.cli.commands

import org.jline.terminal.Terminal
import org.springframework.shell.component.flow.ComponentFlow
import org.springframework.shell.component.flow.SelectItem
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import tech.goksi.tabbyfiles.services.RoleService
import tech.goksi.tabbyfiles.services.TabbyUserService

@ShellComponent
@ShellCommandGroup("User Commands")
class UserCommand(
    private val userService: TabbyUserService,
    private val roleService: RoleService,
    private val flowBuilder: ComponentFlow.Builder,
    private val terminal: Terminal
) {
    @ShellMethod(key = ["user-list"], value = "Shows list of all currently registered users")
    fun list() {

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
        val rolesToGive = resultContext.get<List<String>>("roles").map { str -> roles.find { it.name == str } }
        terminal.writer().println("$username $password $rolesToGive")
    }

}