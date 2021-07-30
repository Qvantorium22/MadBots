import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
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
    launch {
        interactor.connect()
    }
    launch(Dispatchers.IO) {
        interactor.markFlow.collect {
            it?.forEach { o ->
                o.forEach {
                    print("$it ")
                }
                println()
            }
        }
    }

    launch {
        while (true) {
            interactor.playerTurn(firstPlayer)
            interactor.playerTurn(secondPlayer)
        }
    }.join()
}
