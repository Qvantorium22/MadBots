package core

import kotlinx.coroutines.flow.*

abstract class MadInteractor(
    private val matrix: Matrix
) : ChangeMatrix {

    abstract fun transform(value: Int): Markelable

    private val _markFlow: MutableStateFlow<List<List<Markelable>>?> = MutableStateFlow(null)
    val markFlow: StateFlow<List<List<Markelable>>?> = _markFlow

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