package tech.goksi.tabbyfiles.utils.version.http.client

import org.springframework.web.service.annotation.GetExchange
import tech.goksi.tabbyfiles.utils.version.http.response.GithubResponse

interface GithubClient {

    @GetExchange("/releases/latest")
    fun getResponse(): GithubResponse
}