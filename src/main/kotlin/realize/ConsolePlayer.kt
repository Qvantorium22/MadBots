package realize

import core.MadPlayer
import java.awt.Point

class ConsolePlayer : MadPlayer {
    override fun turn(): Point {
        return Point(
            readLine()!!.toInt(),
            readLine()!!.toInt()
        )
    }
}
