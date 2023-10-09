package tech.goksi.tabbyfiles.interceptors

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.method.HandlerMethod
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor
import tech.goksi.tabbyfiles.annotations.AdminAccess
import tech.goksi.tabbyfiles.models.TabbyUser

class AdminAccessInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) return true
        if (!handler.hasMethodAnnotation(AdminAccess::class.java)) return true
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal !is TabbyUser) return true
        if (principal.isAdmin()) return true
        else throw ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "You are not authorized to access this endpoint !"
        )
    }
}