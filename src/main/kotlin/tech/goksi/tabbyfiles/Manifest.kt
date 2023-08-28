package tech.goksi.tabbyfiles

import com.fasterxml.jackson.databind.JsonNode

class Manifest(private val jsonNode: JsonNode) {

    fun getJsBundlePath(): String {
        return "/static/${jsonNode.get("src/index.tsx").get("file").asText()}"
    }

    fun getCssPath(): String {
        return "/static/${jsonNode.get("src/index.tsx").get("css").get(0).asText()}"
    }
}