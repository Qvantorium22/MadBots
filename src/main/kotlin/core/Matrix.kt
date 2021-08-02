package core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Matrix(
    override val weight: Int,
    override val height: Int,
    override val autoResize: Boolean
) : MatrixConfig {
    companion object {
        private const val defaultMark = 0
    }

    private val _dataFlow: MutableStateFlow<ArrayList<ArrayList<Int>>> = MutableStateFlow(arrayListOf(arrayListOf()))
    val dataFlow: StateFlow<ArrayList<ArrayList<Int>>?> = _dataFlow

    init {
        for (i in 0 until weight) {
            val line = arrayListOf<Int>()
            for (j in 0 until height) {
                line.add(defaultMark)
            }
            _dataFlow.value.add(line)
        }
    }


    fun setMark(point: Point, value: Int) {
        _dataFlow.value[point.y][point.x] = value
        update()
    }

    fun deleteMark(point: Point) {
        _dataFlow.value[point.y][point.x] = 0
        update()
    }

    private fun update() {
        _dataFlow.value = _dataFlow.value.clone() as ArrayList<ArrayList<Int>>
    }
}