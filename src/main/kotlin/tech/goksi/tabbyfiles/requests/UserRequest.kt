package tech.goksi.tabbyfiles.requests

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern


import tech.goksi.tabbyfiles.models.Role

data class UserRequest(
    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{4,15}\$",
        message = "Username must have between 4 and 15 chars with no special characters !"
    )
    val username: String,
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{6,40}\$",
        message = "Password must be between 6 and 40 chars with at least 1 lowercase, 1 uppercase and 1 special character !"
    )
    val password: String,
    @field:NotEmpty(message = "User must have at least one role !")
    val roles: List<Role>
)