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

    private var _currentPlayer: MadPlayer = firstPlayer

    private fun swapPlayer() {
        _currentPlayer = if (_currentPlayer == firstPlayer)
            secondPlayer
        else
            firstPlayer
    }

    fun playerTurn(player: ConsolePlayer) {
        if (player == _currentPlayer) {
            insertMark(player.turn(), player.mark)
            swapPlayer()
        }
    }
}