package core

interface MadPlayer {
    fun turn() : Point
    fun turn(x: Int, y: Int) = Point(x, y)
}
