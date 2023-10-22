package tech.goksi.tabbyfiles.controllers

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tech.goksi.tabbyfiles.annotations.AdminAccess
import tech.goksi.tabbyfiles.models.TabbyUser
import tech.goksi.tabbyfiles.requests.UserRequest
import tech.goksi.tabbyfiles.services.TabbyUserService

@RestController
@RequestMapping("users", consumes = ["application/json"])
@AdminAccess
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody userRequest: UserRequest): TabbyUser {
        return userService.addUser(userRequest)
    }
}