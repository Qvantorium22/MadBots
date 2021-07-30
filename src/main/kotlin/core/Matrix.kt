package core

import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.*

class Matrix(
    override val weight: Int,
    override val height: Int,
    override val autoResize: Boolean
) : MatrixConfig {

    private val _data: MutableList<MutableList<Int>> = mutableListOf()

    private val _dataFlow: MutableStateFlow<Array<Array<Int>>?> = MutableStateFlow(null)
    val dataFlow: StateFlow<Array<Array<Int>>?> = _dataFlow

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
        update()
    }

    fun deleteMark(point: Point) {
        _data[point.y][point.x] = 0
        update()
    }

    private fun update() {
        _dataFlow.value = Array(_data.size) { x ->
            Array(_data[x].size) { y ->
                _data[x][y]
            }
        }
    }
}