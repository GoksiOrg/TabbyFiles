package tech.goksi.tabbyfiles.configuration

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.web.util.UriUtils
import tech.goksi.tabbyfiles.configuration.handlers.ContinueUrlAuthenticationSuccessHandler as AuthHandler

class SaveRequestAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val desiredURI = request.requestURI
        if (desiredURI == "/") {
            response.sendRedirect(SecurityConfiguration.LOGIN_URL)
        } else {
            response.sendRedirect(
                "${SecurityConfiguration.LOGIN_URL}?${AuthHandler.QUERY_PARAMETER}=${
                    UriUtils.encodePath(
                        desiredURI,
                        Charsets.UTF_8
                    )
                }"
            )
        }
    }
}