package tech.goksi.tabbyfiles.configuration.convertors

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.awt.Color

class AwtColorSerializer : StdSerializer<Color>(Color::class.java) {
    override fun serialize(value: Color?, gen: JsonGenerator, provider: SerializerProvider) {
        val result = if (value != null) {
            String.format("#%06X", value.rgb and 0xFFFFFF)
        } else ""
        gen.writeString(result)
    }
}