package core

import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.*

class Matrix(
    override val weight: Int,
    override val height: Int,
    override val autoResize: Boolean
) : MatrixConfig {

    private val _data: MutableList<MutableList<Int>> = mutableListOf()
    private val _dataFlow: MutableStateFlow<Array<Array<Int>>?> = MutableStateFlow(getDataArray())
    val dataFlow: StateFlow<Array<Array<Int>>?> = _dataFlow

    init {
        _dataFlow.value = getDataArray()
    }

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
        return if (point.x < weight && point.y < height)
            _data[point.y][point.x]
        else
            0
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
        _dataFlow.value = getDataArray()
    }

    private fun getDataArray(): Array<Array<Int>> {
        return Array(_data.size) { x ->
            Array(_data[x].size) { y ->
                _data[x][y]
            }
        }
    }
}