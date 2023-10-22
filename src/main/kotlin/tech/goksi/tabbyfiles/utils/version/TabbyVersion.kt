package tech.goksi.tabbyfiles.utils.version

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("tabbyVersion")
class TabbyVersion(
    @Value("\${tabby.version}") private val version: String
) : Comparable<TabbyVersion> {
    companion object {
        const val CANARY_VERSION = "canary"
    }

    private final val internalVersion = version.replace("v", "").split(".")

    init {
        if (internalVersion.size != 3 && version != CANARY_VERSION) {
            throw IllegalStateException("Invalid semantic version style !")
        }
    }

    fun isCanary() = version == CANARY_VERSION

    override fun compareTo(other: TabbyVersion): Int {
        var result = 0
        if (other.isCanary()) return -1 //canary is always latest :P
        for (i in 0..2) {
            val v1 = internalVersion[i].toInt()
            val v2 = other.internalVersion[i].toInt()
            val compare = v1.compareTo(v2)
            if (compare != 0) {
                result = compare
                break
            }
        }
        return result
    }

    override fun toString(): String {
        return version
    }
}