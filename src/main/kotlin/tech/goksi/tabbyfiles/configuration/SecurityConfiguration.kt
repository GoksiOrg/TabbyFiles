package tech.goksi.tabbyfiles.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
class SecurityConfiguration {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests {
            //TODO: custom error page
            it.requestMatchers(antMatcher("/error"), antMatcher("/static/**")).permitAll()
            it.anyRequest().authenticated()
        }
            .formLogin {
                it.defaultSuccessUrl("/")
                it.loginPage("/auth/login/").permitAll()
                it.loginProcessingUrl("/auth/login/")
                it.failureForwardUrl("/auth/login?error=true")
            }
            .logout {
                it.logoutUrl("/auth/logout/")
            }
            .cors(Customizer.withDefaults())
            .csrf {
                it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                it.csrfTokenRequestHandler(CsrfTokenRequestAttributeHandler())
            }

        return httpSecurity.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}