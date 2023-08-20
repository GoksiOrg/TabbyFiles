package tech.goksi.tabbyfiles.models

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    val id: Long,
    @Column(unique = true)
    val name: String,
    val admin: Boolean,
    @Column(name = "max_upload", nullable = false)
    val maxUpload: Long,
    @ManyToMany(mappedBy = "roles")
    val users: List<TabbyUser>,
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
}