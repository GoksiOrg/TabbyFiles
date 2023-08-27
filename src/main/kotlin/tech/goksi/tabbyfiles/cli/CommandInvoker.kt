package tech.goksi.tabbyfiles.cli

import tech.goksi.tabbyfiles.cli.commands.Command
import tech.goksi.tabbyfiles.utils.SLF4J
import java.util.*

class CommandInvoker {

    private val commandMap: Map<String, Command>
    private val logger by SLF4J

    init {
        commandMap = HashMap()
    }

    fun invoke(args: Queue<String>) {
        if (args.isEmpty()) {
            logger.warn("You must pass command after CLI parameter ! Example: \"java -jar Tabby.jar cli <command>\"")
            return
        }
        val commandName = args.poll()
        val command = commandMap[commandName]
        if (command == null) {
            logger.warn("Command $commandName not found, you can maybe try with help command ?")
            return
        }
        command.execute(args)
    }
}