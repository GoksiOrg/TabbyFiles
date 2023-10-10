package tech.goksi.tabbyfiles.utils.version

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import tech.goksi.tabbyfiles.utils.SLF4J

@Component
class VersionChecker(
    private val version: Version
) {
    companion object {
        const val API_URL = "https://api.github.com/repos/GoksiOrg/TabbyFiles/releases/latest"
    }

    val logger by SLF4J


    /*TODO: don't like idea of PostConstruct, might do with event*/
    @PostConstruct
    fun checkVersion() {
        logger.info("Checking latest TabbyFiles version...")
        if (version.isCanary()) {
            logger.warn("You are running canary version of TabbyFiles, some things might be unstable.")
        }
    }
}