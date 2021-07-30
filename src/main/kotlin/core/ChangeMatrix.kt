package core

import realize.Mark

interface ChangeMatrix {
    fun insertMark(point: Point, mark: Mark)

    fun deleteMark(point: Point)

    fun changeMark(point: Point, mark: Mark){
        deleteMark(point)
        insertMark(point, mark)
    }

    fun copyMark(fromPoint: Point, toPoint: Point)
}