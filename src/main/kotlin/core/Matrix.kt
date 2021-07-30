package core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Matrix(
    override val weight: Int,
    override val height: Int,
    override val autoResize: Boolean
) : MatrixConfig {

    private val _data: ArrayList<ArrayList<Int>> = arrayListOf()

    private val _dataFlow = MutableStateFlow(_data)
    val dataFlow : StateFlow<ArrayList<ArrayList<Int>>> = _dataFlow

    init {
        for (i in 0 until weight) {
            val line = arrayListOf<Int>()
            for (j in 0 until height) {
                line.add(0)
            }
            _data.add(line)
        }
    }

    fun getPole(point: Point): Int {
        return _data[point.y][point.x]
    }

    fun setMark(point: Point, value: Int) {
        _data[point.y][point.x] = value
    }

    fun deleteMark(point: Point) {
        _data[point.y][point.x] = 0
    }
}