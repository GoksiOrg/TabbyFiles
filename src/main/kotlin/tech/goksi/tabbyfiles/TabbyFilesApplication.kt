package tech.goksi.tabbyfiles

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class TabbyFilesApplication

fun main(args: Array<String>) {
    val configurationFile = File("application.yml")
    if (!configurationFile.exists()) {
        val logger = LoggerFactory.getLogger(TabbyFilesApplication::class.java)
        logger.info("Looks like you don't have configuration YAML file, generating one...")
        val configurationContent = TabbyFilesApplication::class.java.classLoader
            .getResource("default-application.yml")!!.readText()
        configurationFile.writeText(configurationContent)
        logger.info("Configuration file generated, edit it to your desires and start application again !")
        return
    }
    runApplication<TabbyFilesApplication>(*args)
}
