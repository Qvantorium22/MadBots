package sudoku

fun main(){
    val fullPole = MutableList(9) { MutableList(9) { 0 } }
    fullPole[0] = horizLineGenerate()
    for (i in 1 until fullPole.size-6){//3 rows
        fullPole[i] = shiftLine(fullPole[i-1])
    }
    for (i in 3 until fullPole.size-3){//3-6 rows
        fullPole[i] = mixLineInBlock(fullPole[i-3])
    }
    for (i in 6 until fullPole.size){//6-9 rows
        fullPole[i] = mixLineInBlock(fullPole[i-3])
    }
    for (i in fullPole.indices){
        for (j in fullPole.indices)
            print("${fullPole[i][j]}  ")
        println()
    }
}
fun horizLineGenerate(): MutableList<Int> {
    val line = MutableList(9) { 0 }
    val str = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    for (i in 0 until 9){
        val ind = IntRange(0, str.size-1).random()
        line[i] = str[ind]
        str.remove(str[ind])
    }
    return line
}

fun shiftLine(line: MutableList<Int>): MutableList<Int>{
    val newLine = mutableListOf<Int>()
    for (i in 3 until line.size){
        newLine.add(line[i])
    }
    newLine.add(line[0])
    newLine.add(line[1])
    newLine.add(line[2])

    return newLine
}
fun mixLineInBlock(line: MutableList<Int>): MutableList<Int> {
    val newLine = mutableListOf<Int>()

    newLine.add(line[1])
    newLine.add(line[2])
    newLine.add(line[0])
    newLine.add(line[4])
    newLine.add(line[5])
    newLine.add(line[3])
    newLine.add(line[7])
    newLine.add(line[8])
    newLine.add(line[6])

    return newLine
}