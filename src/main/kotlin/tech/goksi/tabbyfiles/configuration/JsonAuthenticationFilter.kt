package tech.goksi.tabbyfiles.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod.POST
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher


class JsonAuthenticationFilter(authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    companion object {
        val objectMapper = ObjectMapper()
    }


    init {
        setRequiresAuthenticationRequestMatcher(antMatcher(POST, "/auth/login"))
        this.authenticationManager = authManager
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        if (request.contentType != "application/json") return super.attemptAuthentication(
            request,
            response
        )
        request.inputStream.use {
            val authRequest = objectMapper.readValue<AuthenticationRequest>(it)
            val token = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
            return this.authenticationManager.authenticate(token);
        }
    }
}

private data class AuthenticationRequest(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("password")
    val password: String
)

