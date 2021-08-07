package realize

import core.Markelable

enum class Mark(override var value: Int, val sym : Char) : Markelable {
    NOTHING(0, '-'),
    X(1, 'X'),
    Y(2, 'O')
}