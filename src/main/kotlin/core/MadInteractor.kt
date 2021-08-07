package core

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.IntSize
import core.validators.PoleIsEmptyValidators
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

abstract class MadInteractor<T : Markelable>(
    val matrix: Matrix
) : ChangeMatrix<T> {

    abstract fun transform(value: Int): T

    abstract fun checkWin(): Int

    val stateWin = mutableStateOf("")

    private val _markFlow: MutableStateFlow<List<List<T>>?> = MutableStateFlow(null)

    val markFlow: Flow<List<List<T>>?> = matrix.dataFlow.map { row ->
        var list = listOf<List<T>>()
        if (row != null) {
            list = List(row.size) { collumn ->
                List(row[collumn].size) { y ->
                    transform(row[collumn][y])
                }
            }
        } else
            println(row)
        return@map list
    }

    fun getDataFlow() = matrix.dataFlow

    private val matrixValidators = mutableListOf(
        PoleIsEmptyValidators {
            matrix.dataFlow.value!![it.y][it.x]
        }
    )

    override fun insertMark(point: Point, mark: T): Boolean {
        return if (validate(point).find { it is ValidateResult.Error } == null) {
            matrix.setMark(point, mark.value)
            true
        } else {
            println(validate(point).find { it is ValidateResult.Error }.toString())
            false
        }
    }

    override fun deleteMark(point: Point): Boolean {
        matrix.deleteMark(point)
        return true
    }

    override fun copyMark(fromPoint: Point, toPoint: Point): Boolean {
        matrix.setMark(toPoint, getMark(fromPoint).value)
        return true
    }

    override fun getMark(point: Point): T {
        return if (point.y in 0 until matrix.height && point.x in 0 until matrix.weight)
            transform(matrix.dataFlow.value!![point.y][point.x])
                else transform(0)
    }

    private fun validate(point: Point): List<ValidateResult> {
        return matrixValidators.map { it.validate(point) }
    }

    fun getSizeMatrix() = IntSize(matrix.weight, matrix.height)
}