package tech.goksi.tabbyfiles.configuration.thymeleaf

import com.fasterxml.jackson.databind.ObjectMapper
import org.thymeleaf.standard.serializer.IStandardJavaScriptSerializer
import java.io.Writer

class JacksonTemplateSerializer(private val objectMapper: ObjectMapper) : IStandardJavaScriptSerializer {
    override fun serializeValue(value: Any, writer: Writer) {
        writer.write(objectMapper.writeValueAsString(value))
    }
}