package tech.goksi.tabbyfiles.cli.commands

import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.commands.Version
import tech.goksi.tabbyfiles.cli.services.ConsoleServices
import tech.goksi.tabbyfiles.utils.version.TabbyVersion

@ShellComponent
@ShellCommandGroup("Utility Commands")
class VersionCommand(
    private val currentVersion: TabbyVersion,
    private val console: ConsoleServices
) : Version.Command {

    @ShellMethod(key = ["version"], value = "Show version info")
    fun version() {
        if (currentVersion.isCanary()) {
            console.warning("You are currently using canary version of TabbyFiles, consider using stable release !")
        } else {
            console.write("You are currently running %s of TabbyFiles", currentVersion)
        }
    }
}