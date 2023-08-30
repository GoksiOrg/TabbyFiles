package tech.goksi.tabbyfiles.cli.commands.api

import tech.goksi.tabbyfiles.utils.SLF4J
import java.util.*

abstract class Command(
    val name: String,
    val description: String,
) {
    companion object {
        val logger by SLF4J
    }

    open fun execute(args: Queue<String>): Boolean {
        if (!args.isEmpty() && args.peek().equals("help", true)) {
            logger.info(getDefaultHelp())
            return true
        }
        return false
    }

    abstract fun getDefaultHelp(): String
}