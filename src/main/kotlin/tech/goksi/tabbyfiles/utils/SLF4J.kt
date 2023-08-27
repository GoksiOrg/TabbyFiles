package tech.goksi.tabbyfiles.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KProperty

object SLF4J {
    operator fun getValue(refClass: Any?, prop: KProperty<*>): Logger {
        return LoggerFactory.getLogger(refClass!!::class.java)
    }

    operator fun invoke(name: String) = lazy {
        LoggerFactory.getLogger(name)
    }
}