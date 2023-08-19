package tech.goksi.tabbyfiles.services

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import tech.goksi.tabbyfiles.models.TabbyUser
import tech.goksi.tabbyfiles.repositories.TabbyUserRepository

@Service
@Transactional
class TabbyUserService(private val tabbyUserRepository: TabbyUserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return tabbyUserRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Username ${username ?: ""} not found !") }
    }

    fun getAllUsers(): List<TabbyUser> {
        return tabbyUserRepository.findAll()
    }

    fun getUserById(id: Long): TabbyUser {
        return tabbyUserRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with id $id is not found") }
    }

    fun getUserByUsername(username: String): TabbyUser {
        return tabbyUserRepository.findByUsername(username)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "User with username $username is not found") }
    }
}