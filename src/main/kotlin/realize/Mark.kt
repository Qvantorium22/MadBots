package realize

import core.Markelable

enum class Mark(override val value: Int, val sym : Char) : Markelable {
    X(0, 'X'),
    Y(1, 'O')
}