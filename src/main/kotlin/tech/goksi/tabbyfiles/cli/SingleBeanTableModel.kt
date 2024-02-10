package tech.goksi.tabbyfiles.cli

import org.springframework.beans.BeanWrapper
import org.springframework.beans.BeanWrapperImpl
import org.springframework.shell.table.TableModel


class SingleBeanTableModel<T : Any>(obj: T, headers: LinkedHashMap<String, Any>) : TableModel() {
    private val data: BeanWrapper = BeanWrapperImpl(obj)
    private val propertyNames: List<String> = ArrayList(headers.keys)
    private val headerRow: List<Any> = ArrayList(headers.values)

    override fun getRowCount() = 2 // as we have 1 for headers and 1 for actual data

    override fun getColumnCount() = propertyNames.size

    override fun getValue(row: Int, column: Int): Any? {
        return if (row == 0) {
            headerRow[column]
        } else {
            val propertyName = propertyNames[column]
            data.getPropertyValue(propertyName)
        }
    }
}