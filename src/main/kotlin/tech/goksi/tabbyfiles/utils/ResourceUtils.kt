package tech.goksi.tabbyfiles.utils

import java.io.File

object ResourceUtils {
    private fun readResource(name: String): String {
        return ResourceUtils::class.java.classLoader
            .getResource("default-application.yml")!!.readText()
    }

    fun writeResource(file: File, name: String) {
        file.writeText(readResource(name))
    }
}