package tech.goksi.tabbyfiles.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import tech.goksi.tabbyfiles.models.Role
import tech.goksi.tabbyfiles.repositories.RoleRepository
import tech.goksi.tabbyfiles.requests.RoleRequest
import java.awt.Color

@Service
class RoleService(private val roleRepository: RoleRepository) {
    fun getAllRoles(): List<Role> {
        return roleRepository.findAll()
    }

    fun addRole(name: String, color: Color, admin: Boolean, maxUpload: Long): Role {
        val role = Role(
            name = name,
            color = color,
            admin = admin,
            maxUpload = maxUpload
        )
        return roleRepository.save(role)
    }

    fun addRole(roleRequest: RoleRequest) = addRole(
        roleRequest.name,
        Color.decode("#${roleRequest.color}"),
        roleRequest.admin,
        roleRequest.maxUpload
    )

    fun getRoleByName(name: String): Role {
        return roleRepository.findByName(name)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Role with name $name is not found !") }
    }

    fun getAllAdminRoles(): List<Role> {
        return roleRepository.findByAdmin(true)
    }
}