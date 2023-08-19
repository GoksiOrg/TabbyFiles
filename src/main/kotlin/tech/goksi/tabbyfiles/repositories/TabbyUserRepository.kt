package tech.goksi.tabbyfiles.repositories

import org.springframework.data.jpa.repository.JpaRepository
import tech.goksi.tabbyfiles.models.TabbyUser
import java.util.*

interface TabbyUserRepository : JpaRepository<TabbyUser, Long> {
    fun findByUsername(username: String?): Optional<TabbyUser>
}