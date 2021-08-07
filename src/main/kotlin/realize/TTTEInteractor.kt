package realize

import androidx.compose.runtime.MutableState
import core.*
import kotlinx.coroutines.flow.collect

class TTTEInteractor(
    val firstPlayer: MadPlayer,
    val secondPlayer: MadPlayer
) : MadInteractor<Mark>(
    Matrix(3, 3, false)
) {
    override fun transform(value: Int): Mark {
        return Mark.values().find { value == it.value } ?: Mark.X
    }

    override fun checkWin(): Int {
        val needLength = 3
        val matrixData = matrix.dataFlow.value
        if (matrixData != null){
            var currentPole : Int
            for (y in matrixData.indices){
                for (x in matrixData[y].indices){
                    if (getMark(Point(x, y)).value != 0){
                        currentPole = getMark(Point(x, y)).value
                        when(x){
                            0 -> {
                                if (checkPole(Point(x, y), Point(x, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }

                                if (checkPole(Point(x, y), Point(x + 1, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x + inc, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }

                                if (checkPole(Point(x, y), Point(x + 1, y))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x + inc, y)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }
                            }

                            in 1..matrix.weight-2 ->{
                                if (checkPole(Point(x, y), Point(x - 1, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x - inc, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }

                                if (checkPole(Point(x, y), Point(x, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }

                                if (checkPole(Point(x, y), Point(x + 1, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x + inc, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }

                                if (checkPole(Point(x, y), Point(x + 1, y))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x + inc, y)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }
                            }
                            matrix.weight-1 -> {
                                if (checkPole(Point(x, y), Point(x - 1, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x - inc, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }

                                if (checkPole(Point(x, y), Point(x, y + 1))){
                                    var inc = 2
                                    while (needLength > inc){
                                        if (checkPole(Point(x, y), Point(x, y + inc)))
                                            inc++
                                        else
                                            break
                                    }
                                    if (inc >= needLength){
                                        return currentPole
                                    }
                                }
                            }
                            else -> {
                                return 0
                            }
                        }
                    }
                }
            }
            return 0
        }
        else
            return 0
    }

    private var _currentPlayer: MadPlayer = firstPlayer

    private fun swapPlayer() {
        _currentPlayer = if (_currentPlayer == firstPlayer)
            secondPlayer
        else
            firstPlayer
    }

    fun playerTurn(player: UIPlayer, x: Int, y: Int) {
        if (player == _currentPlayer) {
            if (insertMark(player.turn(x, y), player.mark)) {
                swapPlayer()
                if (checkWin() != 0) {
                    stateWin.value = "win" + checkWin()
                }
            }
        }
    }
    private fun checkPole(firstPoint: Point, secondPoint: Point) = getMark(firstPoint).value == getMark(secondPoint).value
}