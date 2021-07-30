package core

import core.validators.PoleIsEmptyValidators
import kotlinx.coroutines.flow.*

abstract class MadInteractor(
    val matrix: Matrix
) : ChangeMatrix {

    abstract fun transform(value: Int): Markelable

    private val _markFlow: MutableStateFlow<List<List<Markelable>>?> = MutableStateFlow(null)
    val markFlow: Flow<List<List<Markelable>>?> = matrix.dataFlow.map { row ->
        var list = listOf<List<Markelable>>()
        if (row != null) {
            list = List(row.size) { collumn ->
                List(row[collumn].size) { y ->
                    transform(row[collumn][y])
                }
            }
        }
        else
            println(row)
        return@map list
    }

    suspend fun connect() {
        matrix.dataFlow.collect { row ->
            if (row != null)
                _markFlow.value = List(row.size) { collumn ->
                    List(row[collumn].size) { y ->
                        transform(row[collumn][y])
                    }
                }
        }
    }

    private val matrixValidators = mutableListOf(
        PoleIsEmptyValidators { matrix.getPole(it) }
    )

    override fun insertMark(point: Point, mark: Markelable): Boolean {
        return if (validate(point).find { it is ValidateResult.Error } == null) {
            matrix.setMark(point, mark.value)
            true
        } else {
            println(validate(point).find{ it is ValidateResult.Error }.toString())
            false
        }
    }

    override fun deleteMark(point: Point): Boolean {
        matrix.deleteMark(point)
        return true
    }

    override fun copyMark(fromPoint: Point, toPoint: Point): Boolean {
        matrix.setMark(toPoint, matrix.getPole(fromPoint))
        return true
    }

    private fun validate(point: Point): List<ValidateResult> {
        return matrixValidators.map { it.validate(point) }
    }
}