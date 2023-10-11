package tech.goksi.tabbyfiles.utils.version.http.client

import org.springframework.web.service.annotation.GetExchange
import tech.goksi.tabbyfiles.utils.version.http.response.GithubReleaseResponse

interface GithubClient {

    @GetExchange("/releases/latest")
    fun getLatestRelease(): GithubReleaseResponse
}