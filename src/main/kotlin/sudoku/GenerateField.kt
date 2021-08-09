package sudoku

fun main() {

//    val fullPoleA = generatePole(length = 4, seed = "")

    val fullPole = MutableList(9) { MutableList(9) { 0 } }

    fullPole[0] = horizLineGenerate(9)

    for (i in 1 until fullPole.size - 6) {//3 rows
        fullPole[i] = shiftLine(fullPole[i - 1], 3)
    }
    for (i in 3 until fullPole.size - 3) {//3-6 rows
        fullPole[i] = mixLineInBlock(fullPole[i - 3])
    }
    for (i in 6 until fullPole.size) {//6-9 rows
        fullPole[i] = mixLineInBlock(fullPole[i - 3])
    }
    for (i in fullPole.indices) {
        for (j in fullPole.indices)
            print("${fullPole[i][j]}  ")
        println()
    }
}

fun horizLineGenerate(length: Int) = MutableList(length) { it + 1 }
    .shuffled().toMutableList()

fun shiftLine(line: MutableList<Int>, step: Int) =
    (line.drop(line.size - step) + line.dropLast(step))
        .toMutableList()


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