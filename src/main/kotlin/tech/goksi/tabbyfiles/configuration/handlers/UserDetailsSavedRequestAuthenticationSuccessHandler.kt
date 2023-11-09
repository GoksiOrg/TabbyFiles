package tech.goksi.tabbyfiles.configuration.handlers

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import tech.goksi.tabbyfiles.models.TabbyUser

class UserDetailsSavedRequestAuthenticationSuccessHandler(
    forwardUrl: String,
    private val objectMapper: ObjectMapper
) : SavedRequestAwareAuthenticationSuccessHandler() {

    init {
        defaultTargetUrl = forwardUrl
    }

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val principal = authentication.principal
        if (principal is TabbyUser) {
            response.writer.write(objectMapper.writeValueAsString(principal))
        }
        super.onAuthenticationSuccess(request, response, authentication)
    }
}