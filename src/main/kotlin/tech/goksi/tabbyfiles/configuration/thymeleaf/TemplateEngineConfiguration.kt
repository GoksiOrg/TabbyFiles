package tech.goksi.tabbyfiles.configuration.thymeleaf

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.thymeleaf.TemplateEngine
import org.thymeleaf.standard.StandardDialect

@Configuration
class TemplateEngineConfiguration(private val templateEngine: TemplateEngine, private val objectMapper: ObjectMapper) {

    @PostConstruct
    fun customizeTemplateEngine() {
        val standardDialect = templateEngine.dialects.filterIsInstance<StandardDialect>()[0]
        standardDialect.javaScriptSerializer = JacksonTemplateSerializer(objectMapper)
    }
}