package tech.goksi.tabbyfiles.models

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.awt.Color
import java.time.LocalDateTime

@Entity
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    val id: Long,
    @Column(unique = true)
    val name: String,
    val color: Color,
    val admin: Boolean,
    @Column(name = "max_upload", nullable = false)
    val maxUpload: Long,
    @ManyToMany(mappedBy = "roles")
    val users: Set<TabbyUser>,
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (color != other.color) return false
        if (admin != other.admin) return false
        if (maxUpload != other.maxUpload) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + admin.hashCode()
        result = 31 * result + maxUpload.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}