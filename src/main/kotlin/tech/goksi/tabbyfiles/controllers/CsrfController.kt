package tech.goksi.tabbyfiles.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("csrf")
class CsrfController {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun csrf() {
    }
}