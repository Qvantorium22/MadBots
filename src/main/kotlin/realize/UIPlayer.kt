package realize

import core.MadPlayer
import core.Markelable
import core.Point

class UIPlayer(val mark: Markelable): MadPlayer {
    override fun turn(): Point {
        TODO("Not yet implemented")
    }

    override fun turn(x: Int, y: Int): Point {
        return super.turn(x, y)
    }
}