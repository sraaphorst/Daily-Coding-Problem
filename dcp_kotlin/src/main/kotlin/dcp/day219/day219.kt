package dcp.day219
// Connect4.kt
// By Sebastian Raaphorst, 2019.

// Credit to the awesome http://zetcode.com/kotlin/swing

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Container
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JLabel

private enum class Colour {
    YELLOW, BLUE, UNOCCUPIED
}
private typealias Row   = MutableList<Colour>
private typealias Board = MutableList<Row>

class Connect4: JFrame("Connect 4") {
    // Iniitialize the board to a grid of 6 rows and 7 columns.
    private val board: Board = MutableList(7){ MutableList(6){Colour.UNOCCUPIED} }

    init {
        JFrame("Connect 4")
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setLocationRelativeTo(null)
        layout = GridLayout(7, 7)
        val gridLayout: GridLayout = layout as GridLayout

        // Header
        val xyz = (layout as GridLayout)
        val header = (0..6).map {
            val label = JLabel()
            label.background = Color.RED
            label.border = BorderFactory.createLoweredBevelBorder()
            xyz.
        }



    }

}