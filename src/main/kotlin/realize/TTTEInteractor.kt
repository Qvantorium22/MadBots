package realize

import core.*

class TTTEInteractor(
    val firstPlayer: MadPlayer,
    val secondPlayer: MadPlayer
) : MadInteractor(
    Matrix(3, 3, false)
) {
    override fun transform(value: Int): Markelable {
        return Mark.values().find { value == it.value } ?: Mark.X
    }

    override fun checkWin(): Int {
        val needLength = 3
        val matrixData = matrix.dataFlow.value
        if (matrixData != null){
            var currentPole : Int
            for (y in matrixData.indices){
                for (x in matrixData[y].indices){
                    if (matrix.getPole(Point(x, y)) != 0){
                        currentPole = matrix.getPole(Point(x, y))
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

    fun playerTurn(player: ConsolePlayer) {
        if (player == _currentPlayer) {
            println("Ожидание хода: ")
            if (insertMark(player.turn(), player.mark))
                swapPlayer()
        }
    }
    private fun checkPole(firstPoint: Point, secondPoint: Point) = matrix.getPole(firstPoint) == matrix.getPole(secondPoint)
}