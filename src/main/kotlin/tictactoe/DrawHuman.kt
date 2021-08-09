package tictactoe

import core.MadDrawable

class DrawHuman(
    override val data: ArrayList<ArrayList<Mark>>
) : MadDrawable<Mark> {
}