package tech.goksi.tabbyfiles.utils

import java.io.File

object ResourceUtils {
    fun readResource(name: String): String {
        return ResourceUtils::class.java.classLoader
            .getResource(name)!!.readText()
    }

    fun writeResource(file: File, name: String) {
        file.writeText(readResource(name))
    }
}