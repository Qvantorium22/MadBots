import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import realize.ConsolePlayer
import realize.Mark
import realize.TTTEInteractor
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {
    val firstPlayer = ConsolePlayer(Mark.X)
    val secondPlayer = ConsolePlayer(Mark.Y)
    val interactor = TTTEInteractor(
        firstPlayer,
        secondPlayer
    )
    launch {
        interactor.connect()
    }
    launch(Dispatchers.IO) {
        interactor.markFlow.collect {
            it?.forEach { o ->
                o.forEach {
                    print((it as Mark).sym + " ")
                }
                println()
            }
        }
    }
    launch {
        for (i in 0..4) {
            delay(50)
            interactor.playerTurn(firstPlayer)
            delay(50)
            interactor.playerTurn(secondPlayer)
        }
    }.join()

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
