package core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class MadInteractor(
    private val matrix: Matrix
) : ChangeMatrix {

    abstract fun transform(value :Int) :Markelable

    val markFlow : Flow<List<List<Markelable>>> = matrix.dataFlow. map { row->
        List(row.size) { collumn ->
            List(row[collumn].size) {
                transform(it)
            }
        }
    }

    private val matrixValidators = mutableListOf<MadMatrixValidator>()

    override fun insertMark(point: Point, mark: Markelable): Boolean {
        if (validate(point).find { it is ValidateResult.Error } == null) {
            matrix.setMark(point, mark.value)
            return true
        }
        return false
    }

    override fun deleteMark(point: Point): Boolean {
        matrix.deleteMark(point)
        return true
    }

    override fun copyMark(fromPoint: Point, toPoint: Point): Boolean {
        matrix.setMark(toPoint, matrix.getPole(fromPoint))
        return true
    }

    fun validate(point: Point): List<ValidateResult> {
        return matrixValidators.map { it.validate(point) }
    }
}