package tech.goksi.tabbyfiles.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerTypePredicate
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import tech.goksi.tabbyfiles.interceptors.AdminAccessInterceptor

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {

    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController::class.java))
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AdminAccessInterceptor())
    }
}