package sudoku

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GenerateFieldKtTest {
    val fullPole = MutableList(9) { MutableList(9) { it + 1 } }

    @Test
    fun shiftLine() {
        for (i in 1 until fullPole.size - 6) {//3 rows
            fullPole[i] = shiftLine(fullPole[i - 1])
        }
        for (i in 3 until fullPole.size - 3) {//3-6 rows
            fullPole[i] = mixLineInBlock(fullPole[i - 3])
        }
        for (i in 6 until fullPole.size) {//6-9 rows
            fullPole[i] = mixLineInBlock(fullPole[i - 3])
        }

        for (i in fullPole.indices) {
            for (j in fullPole.indices)
                print("${fullPole[i][j]}  ")
            println()
        }
    }
}