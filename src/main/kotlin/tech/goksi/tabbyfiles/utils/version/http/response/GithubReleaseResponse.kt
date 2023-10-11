package tech.goksi.tabbyfiles.utils.version.http.response

import com.fasterxml.jackson.annotation.JsonProperty
import tech.goksi.tabbyfiles.utils.version.Version

data class GithubReleaseResponse(@JsonProperty("tag_name") private val tagName: String) {
    val version: Version by lazy { Version(tagName) }
}
