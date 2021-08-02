package core

interface ChangeMatrix<T : Markelable> {
    fun insertMark(point: Point, mark: T): Boolean

    fun deleteMark(point: Point): Boolean

    fun changeMark(point: Point, mark: T): Boolean {
        return (deleteMark(point) && insertMark(point, mark))
    }

    fun copyMark(fromPoint: Point, toPoint: Point): Boolean

    fun getMark(point: Point) : T
}