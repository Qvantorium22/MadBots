package core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    private val _dataFlow: MutableStateFlow<ArrayList<ArrayList<MutableState<Int>>>> = MutableStateFlow(arrayListOf(arrayListOf<MutableState<Int>>()))
    val dataFlow: StateFlow<ArrayList<ArrayList<MutableState<Int>>>?> = _dataFlow

    init {
        val data = arrayListOf<ArrayList<MutableState<Int>>>()
        for (i in 0 until weight) {
            val line = arrayListOf<MutableState<Int>>()
            for (j in 0 until height)
                line.add(
                    mutableStateOf(defaultMark)
                )
            data.add(line)
        }
        _dataFlow.value = data
    }


    fun setMark(point: Point, value: Int) {
        _dataFlow.value[point.y][point.x].value = value
        update()
    }

    fun deleteMark(point: Point) {
        _dataFlow.value[point.y][point.x].value = 0
        update()
    }

    fun clearData(){
        val data = arrayListOf<ArrayList<MutableState<Int>>>()
        for (i in 0 until weight) {
            val line = arrayListOf<MutableState<Int>>()
            for (j in 0 until height)
                line.add(mutableStateOf(defaultMark))
            data.add(line)
        }
        _dataFlow.value = data
        update()
    }

    private fun update() {
        _dataFlow.value = _dataFlow.value.clone() as ArrayList<ArrayList<MutableState<Int>>>
    }
}