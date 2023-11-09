package tech.goksi.tabbyfiles.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

/*TODO: IMAGES ?*/
@Entity
class TabbyUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0L,
    @Column(nullable = false, unique = true)
    private val username: String,
    @Column(nullable = false)
    @JsonIgnore
    private val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")]
    )
    val roles: MutableSet<Role>,
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) : UserDetails {
    override fun getAuthorities(): List<GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it.name) }
    }

    fun isAdmin(): Boolean {
        return roles.any { it.admin }
    }

    override fun getPassword() = password

    override fun getUsername() = username

    /*Going to leave all this true for now*/

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TabbyUser) return false

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}