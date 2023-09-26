package tech.goksi.tabbyfiles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import tech.goksi.tabbyfiles.utils.ResourceUtils
import tech.goksi.tabbyfiles.utils.SLF4J
import java.io.File

@SpringBootApplication
class TabbyFilesApplication

fun main(args: Array<String>) {
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
