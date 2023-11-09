package tech.goksi.tabbyfiles.filters

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class JsonAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    authManager: AuthenticationManager,
    private val successHandler: AuthenticationSuccessHandler
) : UsernamePasswordAuthenticationFilter(authManager) {
    init {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/auth/login"))
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        if (request.contentType != "application/json") return super.attemptAuthentication(
            request,
            response
        )
        request.inputStream.use {
            val authRequest = objectMapper.readValue<AuthenticationRequest>(it)
            val token = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
            val authentication = this.authenticationManager.authenticate(token)
            successHandler.onAuthenticationSuccess(request, response, authentication)
            return authentication
        }
    }
}

private data class AuthenticationRequest(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("password")
    val password: String
)