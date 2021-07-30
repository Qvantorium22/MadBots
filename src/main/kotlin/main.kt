import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import realize.ConsolePlayer
import realize.Mark
import realize.TTTEInteractor

fun main() = runBlocking {
    val firstPlayer = ConsolePlayer(Mark.X)
    val secondPlayer = ConsolePlayer(Mark.Y)
    val interactor = TTTEInteractor(
        firstPlayer,
        secondPlayer
    )
    interactor.markFlow.collect {
        it.forEach { o ->
            o.forEach {
                print((it as Mark).sym + " ")
            }
            println()
        }
    }
    while (true) {
        interactor.playerTurn(firstPlayer)
        interactor.playerTurn(secondPlayer)
    }
//    val matrixSettings = MatrixSetting(3, 3)
//    val gameRules = GameRules(
//        toWin = 3,
//        autoresize = false
//    )
//
//    val game = Game(matrixSettings, gameRules)
//
//    val player = Player("Hulk")
//    val player1 = Player("Tor")
//
//    game.setFirstPlayer(player)
//    game.setSecondPlayer(player)
//
//    game.start()
}