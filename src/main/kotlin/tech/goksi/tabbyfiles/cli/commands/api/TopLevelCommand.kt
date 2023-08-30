package tech.goksi.tabbyfiles.cli.commands.api

import java.util.*

abstract class TopLevelCommand(
    name: String,
    description: String,
    private val subcommands: Map<String, Command>
) : Command(name, description) {
    override fun execute(args: Queue<String>): Boolean {
        if (super.execute(args)) return true
        if (args.isEmpty()) {
            logger.warn(getDefaultHelp())
            return false
        }
        val subcommandName = args.poll()
        val subcommand = subcommands[subcommandName]
        if (subcommand == null) {
            logger.warn(getDefaultHelp())
            return false
        }
        return subcommand.execute(args)
    }
}