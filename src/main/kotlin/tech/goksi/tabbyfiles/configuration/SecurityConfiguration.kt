package tech.goksi.tabbyfiles.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher


@Configuration
class SecurityConfiguration(private val objectMapper: ObjectMapper) {
    companion object {
        const val LOGIN_URL = "/auth/login"
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity, authManager: AuthenticationManager): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests {
            //TODO: custom error page
            it.requestMatchers(
                antMatcher("/error"), antMatcher("/static/**"), antMatcher("/api/csrf"), antMatcher(
                    LOGIN_URL
                )
            ).permitAll()
            it.anyRequest().authenticated()
        }
            .formLogin {
                it.defaultSuccessUrl("/")
                it.loginPage(LOGIN_URL).permitAll()
                it.failureUrl("$LOGIN_URL?error=true")
            }.addFilterAt(
                JsonAuthenticationFilter(objectMapper, authManager), UsernamePasswordAuthenticationFilter::class.java
            )
            .logout {
                it.logoutUrl("/auth/logout/")
            }
            .cors(Customizer.withDefaults())
            .csrf {
                it.csrfTokenRequestHandler(CsrfTokenRequestAttributeHandler())
            }
            .exceptionHandling {
                it.authenticationEntryPoint(RestAwareEntryPoint(LOGIN_URL))
            }

        return httpSecurity.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.getAuthenticationManager()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}