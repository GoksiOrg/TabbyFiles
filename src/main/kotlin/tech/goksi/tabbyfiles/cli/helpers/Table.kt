package tech.goksi.tabbyfiles.cli.helpers


class Table(private val headers: List<String>) {
    companion object {
        const val HORIZONTAL = "-"
        const val VERTICAL = "|"
        const val JOINER = "+"
    }

    private val rows = ArrayList<List<String>>()


    fun addRow(vararg row: Any) {
        if (row.size != headers.size)
            throw IllegalArgumentException("Number of row elements should be equal to number of elements in header !")
        
    }

}