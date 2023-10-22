package tech.goksi.tabbyfiles.utils.version.http.response

import com.fasterxml.jackson.annotation.JsonProperty
import tech.goksi.tabbyfiles.utils.version.TabbyVersion

data class GithubReleaseResponse(@JsonProperty("tag_name") private val tagName: String) {
    val version: TabbyVersion by lazy { TabbyVersion(tagName) }
}
