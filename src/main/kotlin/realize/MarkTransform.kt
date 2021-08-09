package realize

object MarkTransform {
    fun transformToMark(value: Int) = Mark.values().find { it.value == value }
}