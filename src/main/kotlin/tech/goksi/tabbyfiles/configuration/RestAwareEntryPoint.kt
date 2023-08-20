package tech.goksi.tabbyfiles.configuration

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponse.SC_NO_CONTENT
import jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

class RestAwareEntryPoint(loginFormUrl: String) : LoginUrlAuthenticationEntryPoint(loginFormUrl) {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        if (isPreflightRequest(request!!)) {
            response?.status = SC_NO_CONTENT
        } else if (isRestRequest(request)) {
            response?.sendError(SC_UNAUTHORIZED, "You are not authorized to access this endpoint !")
        } else {
            super.commence(request, response, authException)
        }
    }

    private fun isPreflightRequest(request: HttpServletRequest): Boolean {
        return "OPTIONS" == request.method;
    }

    private fun isRestRequest(request: HttpServletRequest): Boolean {
        return request.getHeader("Accept") == "application/json"
    }
}