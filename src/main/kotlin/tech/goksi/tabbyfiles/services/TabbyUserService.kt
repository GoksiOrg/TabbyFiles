package tech.goksi.tabbyfiles.services

import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import tech.goksi.tabbyfiles.models.Role
import tech.goksi.tabbyfiles.models.TabbyUser
import tech.goksi.tabbyfiles.repositories.TabbyUserRepository
import tech.goksi.tabbyfiles.requests.UserRequest

@Service
class TabbyUserService(
    private val tabbyUserRepository: TabbyUserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return tabbyUserRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Username ${username ?: ""} not found !") }
    }

    fun addUser(username: String, password: String, roles: List<Role>): TabbyUser {
        val user = TabbyUser(
            username = username,
            password = passwordEncoder.encode(password),
            roles = roles.toMutableSet()
        )
        return tabbyUserRepository.save(user)
    }

    fun addUser(userRequest: UserRequest) = addUser(
        userRequest.username,
        userRequest.password,
        userRequest.roles
    )


    fun getAllUsers(): List<TabbyUser> {
        return tabbyUserRepository.findAll()
    }

    fun getUserById(id: Long): TabbyUser {
        return tabbyUserRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id is not found") }
    }

    fun getUserByUsername(username: String): TabbyUser {
        return tabbyUserRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User with username $username is not found") }
    }

    fun addRoleToUser(user: TabbyUser, role: Role) {
        user.roles.add(role)
        tabbyUserRepository.save(user)
    }

    fun deleteUser(user: TabbyUser) {
        tabbyUserRepository.delete(user)
    }
}