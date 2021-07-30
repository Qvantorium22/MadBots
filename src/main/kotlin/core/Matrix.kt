package core

class Matrix(override val weight: Int, override val height : Int, override val autoResize: Boolean): MatrixConfig {
    private val data: ArrayList<ArrayList<Int>> = arrayListOf()

    init {
        for (i in 0 until weight) {
            val line = arrayListOf<Int>()
            for (j in 0 until height){
                line.add(0)
            }
            data.add(line)
        }
    }

    fun getPole(point: Point): Int {
        return data[point.y][point.x]
    }

    fun setMark(point: Point, value: Int){
        data[point.y][point.x] = value
    }

    fun deleteMark(point: Point) {
        data[point.y][point.x] = 0
    }

    fun printConsole(){
        data.forEach{
            println(it)
        }
        println()
    }
}