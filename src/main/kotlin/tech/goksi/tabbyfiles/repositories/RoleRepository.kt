package tech.goksi.tabbyfiles.repositories

import org.springframework.data.jpa.repository.JpaRepository
import tech.goksi.tabbyfiles.models.Role

interface RoleRepository : JpaRepository<Role, Long> {
}