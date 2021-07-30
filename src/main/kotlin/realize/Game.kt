package realize

import core.Matrix

class Game {

    val matrix = Matrix()

    val firstPlayer = ConsolePlayer()
    val secondPlayer = ConsolePlayer()

    private var currentPlayer = firstPlayer

    fun start() {
        interactor.playerTurn(
            currentPlayer.turn()
        )
    }

    private fun swapPlayer() {
        currentPlayer = if (currentPlayer == firstPlayer)
            secondPlayer
        else
            firstPlayer
    }
}