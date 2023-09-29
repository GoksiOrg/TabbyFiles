package tech.goksi.tabbyfiles.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import tech.goksi.tabbyfiles.models.Role
import tech.goksi.tabbyfiles.repositories.RoleRepository

@Service
class RoleService(private val roleRepository: RoleRepository) {
    fun getAllRoles(): List<Role> {
        return roleRepository.findAll()
    }

    fun getRoleByName(name: String): Role {
        return roleRepository.findByName(name)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Role with name $name is not found !") }
    }

    fun getAllAdminRoles(): List<Role> {
        return roleRepository.findByAdmin(true)
    }
}