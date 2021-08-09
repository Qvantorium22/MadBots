package tictactoe

import core.MadPlayer
import core.Markelable
import core.Point

class ConsolePlayer(
    val mark: Markelable
) : MadPlayer {
    override fun turn(): Point  {
        return Point(
            readLine()!!.toInt(),
            readLine()!!.toInt()
        )
    }
}
