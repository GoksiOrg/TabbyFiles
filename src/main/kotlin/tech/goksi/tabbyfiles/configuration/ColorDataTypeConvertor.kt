package tech.goksi.tabbyfiles.configuration

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.awt.Color

@Converter(autoApply = true)
class ColorDataTypeConvertor : AttributeConverter<Color, Int> {
    override fun convertToDatabaseColumn(attribute: Color) = attribute.rgb

    override fun convertToEntityAttribute(dbData: Int) = Color(dbData)
}