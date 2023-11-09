package tech.goksi.tabbyfiles.configuration.convertors

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.databind.util.Converter
import org.springframework.stereotype.Component
import tech.goksi.tabbyfiles.models.Role
import tech.goksi.tabbyfiles.services.RoleService

@Component
class RoleConvertor(private val roleService: RoleService) : Converter<String, Role> {
    override fun convert(value: String?): Role {
        return roleService.getRoleByName(value ?: "")
    }

    override fun getInputType(typeFactory: TypeFactory?): JavaType {
        return typeFactory?.constructType(String::class.java)
            ?: throw IllegalStateException("TypeFactory is null")
    }

    override fun getOutputType(typeFactory: TypeFactory?): JavaType {
        return typeFactory?.constructType(Role::class.java)
            ?: throw IllegalStateException("TypeFactory is null")
    }
}