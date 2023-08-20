package tech.goksi.tabbyfiles.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.goksi.tabbyfiles.models.TabbyUser
import tech.goksi.tabbyfiles.services.TabbyUserService


@RestController
@RequestMapping("users", consumes = ["application/json"])
class UserController(private val userService: TabbyUserService) {
    @GetMapping
    fun getAll(): List<TabbyUser> {
        return userService.getAllUsers()
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): TabbyUser {
        return userService.getUserById(id)
    }

    @GetMapping("{username}")
    fun getByUsername(@PathVariable username: String): TabbyUser {
        return userService.getUserByUsername(username)
    }
}