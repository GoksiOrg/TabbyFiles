package tech.goksi.tabbyfiles.cli.services

import org.jline.terminal.Terminal
import org.springframework.boot.ansi.AnsiColor
import org.springframework.boot.ansi.AnsiOutput
import org.springframework.stereotype.Service

@Service
class ConsoleServices(private val terminal: Terminal) {
    fun write(message: String, vararg args: Any) {
        terminal.writer().print("> ")
        terminal.writer().printf(AnsiOutput.toString(AnsiColor.BLUE, message, AnsiColor.DEFAULT) + "%n", *args)
    }

    fun success(message: String, vararg args: Any) {
        terminal.writer().print("SUCCESS> ")
        terminal.writer().printf(AnsiOutput.toString(AnsiColor.GREEN, message, AnsiColor.DEFAULT) + "%n", *args)
    }

    fun error(message: String, vararg args: Any) {
        terminal.writer().print("ERROR> ")
        terminal.writer().printf(AnsiOutput.toString(AnsiColor.RED, message, AnsiColor.DEFAULT) + "%n", *args)
    }

    fun warning(message: String, vararg args: Any) {
        terminal.writer().print("WARNING> ")
        terminal.writer().printf(AnsiOutput.toString(AnsiColor.YELLOW, message, AnsiColor.DEFAULT) + "%n", *args)
    }
}