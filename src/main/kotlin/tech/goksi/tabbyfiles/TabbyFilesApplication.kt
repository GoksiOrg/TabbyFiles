package tech.goksi.tabbyfiles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import tech.goksi.tabbyfiles.cli.CommandInvoker
import tech.goksi.tabbyfiles.utils.ResourceUtils
import tech.goksi.tabbyfiles.utils.SLF4J
import java.io.File
import java.util.*

@SpringBootApplication
class TabbyFilesApplication

fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0].equals("cli", true)) {
        val commandInvoker = CommandInvoker()
        val cliArgs = LinkedList(args.asList())
        commandInvoker.invoke(cliArgs)
        return
    }
    val configurationFile = File("application.yml")
    if (!configurationFile.exists()) {
        val logger by SLF4J
        logger.info("Looks like you don't have configuration YAML file, generating one...")
        ResourceUtils.writeResource(configurationFile, "default-application.yml")
        logger.info("Configuration file generated, edit it to your desires and start application again !")
        return
    }
    runApplication<TabbyFilesApplication>(*args)
}
