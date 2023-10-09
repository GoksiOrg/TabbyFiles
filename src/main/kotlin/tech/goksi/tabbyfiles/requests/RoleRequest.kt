package tech.goksi.tabbyfiles.requests

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern

data class RoleRequest(
    @field:Pattern(
        regexp = "^[a-zA-Z0-9 ]{1,15}\$",
        message = "Role name must be between 1 and 15 characters with no special characters excluding space !"
    )
    val name: String,
    @field:Pattern(
        regexp = "^([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Color must be valid color hex string !"
    )
    val color: String,
    val admin: Boolean = false,
    @field:Max(value = Long.MAX_VALUE, message = "Max upload can't be higher then ${Long.MAX_VALUE} !")
    @field:Min(value = -1, message = "Max upload must be valid number not lower then -1 !")
    val maxUpload: Long
)
