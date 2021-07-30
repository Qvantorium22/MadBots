package core

import realize.Mark

class ChangeMatrixManager(val matrix: Matrix): ChangeMatrix, Rules {

    override fun insertMark(point: Point, mark: Mark) {
        when(val resultValidate = validate(point)) {
            is Validate.Error -> printError(resultValidate)
            is Validate.Success -> matrix.setMark(point, mark.value)
        }
    }

    override fun deleteMark(point: Point) {
        matrix.deleteMark(point)
    }

    override fun copyMark(fromPoint: Point, toPoint: Point) {
        matrix.setMark(toPoint, matrix.getPole(fromPoint))
    }

    override fun validate(point: Point): Validate<String> {
        return if (matrix.getPole(point) == 0)
            Validate.Success("Поле свободно")
        else
            Validate.Error("Поле занято")
    }

    private fun printError(validate: Validate.Error<String>){
        println(validate.error)
    }
}