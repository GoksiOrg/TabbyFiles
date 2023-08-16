package tech.goksi.tabbyfiles.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class SPAController {
    //^(?!.*\.(?:js|css|txt)$).*
    @GetMapping("/{unused:\\b(?!static\\b)\\w+\\b}/**")
    fun proceedToFrontend(@PathVariable unused: String): String {
        return "index"
    }
}