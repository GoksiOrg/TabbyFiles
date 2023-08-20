package tech.goksi.tabbyfiles.models

import jakarta.persistence.*
import jakarta.validation.constraints.Size
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
    val id: Long,
    @Column(nullable = false)
    @Size(min = 4, max = 15, message = "Username must have at least 4 characters !")
    private val username: String,
    @Column(nullable = false)
    private val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "role_id")]
    )
    val roles: List<Role>,
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
}