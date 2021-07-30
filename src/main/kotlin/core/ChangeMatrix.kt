package core

interface ChangeMatrix {
    fun insertMark(point: Point, mark: Markelable): Boolean

    fun deleteMark(point: Point): Boolean

    fun changeMark(point: Point, mark: Markelable): Boolean {
        //TODO can delete without insert
        return (deleteMark(point) && insertMark(point, mark))
    }

    fun copyMark(fromPoint: Point, toPoint: Point): Boolean
}