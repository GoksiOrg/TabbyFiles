package tech.goksi.tabbyfiles.cli.commands

import java.util.*

abstract class Command(
    val name: String,
    val description: String,
) {
    abstract fun execute(args: Queue<String>)
}