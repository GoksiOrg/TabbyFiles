package tech.goksi.tabbyfiles.repositories

import org.springframework.data.jpa.repository.JpaRepository
import tech.goksi.tabbyfiles.models.Role
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>

    fun findByAdmin(admin: Boolean): List<Role>
}