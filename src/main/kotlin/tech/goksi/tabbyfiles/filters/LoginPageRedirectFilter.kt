package tech.goksi.tabbyfiles.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import tech.goksi.tabbyfiles.configuration.SecurityConfiguration

class LoginPageRedirectFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        if (isAuthenticated() && SecurityConfiguration.LOGIN_URL == httpRequest.requestURI) {
            httpResponse.status = HttpStatus.TEMPORARY_REDIRECT.value()
            httpResponse.setHeader("Location", "/")
        }
        chain.doFilter(request, response)
    }

    private fun isAuthenticated(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication == null || authentication is AnonymousAuthenticationToken) {
            false
        } else authentication.isAuthenticated
    }
}