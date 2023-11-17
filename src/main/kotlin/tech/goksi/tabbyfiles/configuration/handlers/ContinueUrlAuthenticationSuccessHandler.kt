package tech.goksi.tabbyfiles.configuration.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

class ContinueUrlAuthenticationSuccessHandler(private val objectMapper: ObjectMapper) :
    SimpleUrlAuthenticationSuccessHandler() {
    companion object {
        const val QUERY_PARAMETER = "continue"
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val next = request.getParameter(QUERY_PARAMETER) ?: "/"
        val responseMap = mapOf("continue" to next)

        response.writer.write(objectMapper.writeValueAsString(responseMap))
    }
}