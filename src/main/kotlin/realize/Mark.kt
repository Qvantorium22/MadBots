package realize

import core.Markelable

enum class Mark(override val value: Int, val sym : Char) : Markelable {
    X(1, 'X'),
    Y(2, 'O')

}