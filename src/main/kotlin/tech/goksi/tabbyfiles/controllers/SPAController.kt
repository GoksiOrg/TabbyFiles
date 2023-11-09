package tech.goksi.tabbyfiles.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import tech.goksi.tabbyfiles.Manifest
import tech.goksi.tabbyfiles.utils.ResourceUtils


@Controller
class SPAController(objectMapper: ObjectMapper) {
    private val manifest: Manifest by lazy {
        val mappingString = ResourceUtils.readResource("static/manifest.json")
        Manifest(objectMapper.readTree(mappingString))
    }

    @GetMapping("/{unused:^(?!api|static).+}/**")
    fun proceedToFrontend(@PathVariable unused: String, model: Model): String {
        model.addAttribute("manifest", manifest)
        return "index"
    }

    @GetMapping("/")
    fun proceedToFrontend(model: Model): String {
        model.addAttribute("manifest", manifest)
        return "index"
    }
}