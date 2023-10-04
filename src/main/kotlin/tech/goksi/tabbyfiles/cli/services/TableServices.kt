package tech.goksi.tabbyfiles.cli.services

import org.springframework.shell.table.BeanListTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder
import org.springframework.stereotype.Service
import tech.goksi.tabbyfiles.cli.SingleBeanTableModel

@Service
class TableServices {

    fun printTable(data: List<Any>, headers: LinkedHashMap<String, Any>) {
        val tableModel = BeanListTableModel(data, headers)
        val tableBuilder = TableBuilder(tableModel)
        tableBuilder.addFullBorder(BorderStyle.fancy_light)
        println(tableBuilder.build().render(80))
    }

    fun printTable(data: Any, headers: LinkedHashMap<String, Any>) {
        val tableModel = SingleBeanTableModel(data, headers)
        val tableBuilder = TableBuilder(tableModel)
        tableBuilder.addFullBorder(BorderStyle.fancy_light)
        println(tableBuilder.build().render(80))
    }
}