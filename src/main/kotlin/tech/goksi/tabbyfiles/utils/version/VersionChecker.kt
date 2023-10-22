package tech.goksi.tabbyfiles.utils.version

import jakarta.annotation.PostConstruct
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import tech.goksi.tabbyfiles.utils.SLF4J
import tech.goksi.tabbyfiles.utils.version.http.client.GithubClient

@Component
class VersionChecker(
    private val version: TabbyVersion
) {

    val logger by SLF4J


    /*TODO: don't like idea of PostConstruct, might do with event*/
    @PostConstruct
    fun checkVersion() {
        logger.info("Checking latest TabbyFiles version...")
        if (version.isCanary()) {
            logger.warn("You are running canary version of TabbyFiles, some things might be unstable.")
            return
        }
        val client = getGithubClient()
        val response = client.getLatestRelease()
        if (response.version > version) {
            logger.warn(
                "You are not running latest version of TabbyFiles ! " +
                        "Your version: $version  Latest version: ${response.version}"
            )
        } else {
            logger.info("You are running latest version of TabbyFiles !")
        }
    }

    fun getGithubClient(): GithubClient {
        val client = WebClient.builder()
            .baseUrl("https://api.github.com/repos/GoksiOrg/TabbyFiles")
            .defaultHeaders {
                it.accept = listOf(MediaType.APPLICATION_JSON)
                it.contentType = MediaType.APPLICATION_JSON
            }
            .build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build()
        return factory.createClient(GithubClient::class.java)
    }
}