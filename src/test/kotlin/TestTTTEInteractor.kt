import core.Point
import org.junit.jupiter.api.Test
import realize.ConsolePlayer
import realize.Mark
import realize.TTTEInteractor
import kotlin.test.AfterTest
import kotlin.test.assertEquals

class TestTTTEInteractor {
    private val firstPlayer = ConsolePlayer(Mark.X)
    private val secondPlayer = ConsolePlayer(Mark.Y)
    private val interactor = TTTEInteractor(firstPlayer, secondPlayer)

    @AfterTest
    fun emptyMatrix(){
        for (y in 0 until interactor.matrix.height){
            for (x in 0 until interactor.matrix.weight){
                interactor.matrix.deleteMark(Point(x, y))
            }
        }
    }
    @Test
    fun checkWinXHorizontal(){
        interactor.matrix.setMark(Point(0, 0), Mark.X.value)
        interactor.matrix.setMark(Point(1, 0), Mark.X.value)
        interactor.matrix.setMark(Point(2, 0), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }

    @Test
    fun checkWinXSecondHorizontal(){
        interactor.matrix.setMark(Point(0, 1), Mark.X.value)
        interactor.matrix.setMark(Point(1, 1), Mark.X.value)
        interactor.matrix.setMark(Point(2, 1), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }


    @Test
    fun checkWinXThirdHorizontal(){
        interactor.matrix.setMark(Point(0, 2), Mark.X.value)
        interactor.matrix.setMark(Point(1, 2), Mark.X.value)
        interactor.matrix.setMark(Point(2, 2), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }

    @Test
    fun checkWinXVertical(){
        interactor.matrix.setMark(Point(0, 0), Mark.X.value)
        interactor.matrix.setMark(Point(0, 1), Mark.X.value)
        interactor.matrix.setMark(Point(0, 2), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }

    @Test
    fun checkWinXSecondVertical(){
        interactor.matrix.setMark(Point(1, 0), Mark.X.value)
        interactor.matrix.setMark(Point(1, 1), Mark.X.value)
        interactor.matrix.setMark(Point(1, 2), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }

    @Test
    fun checkWinXThirdVertical(){
        interactor.matrix.setMark(Point(2, 0), Mark.X.value)
        interactor.matrix.setMark(Point(2, 1), Mark.X.value)
        interactor.matrix.setMark(Point(2, 2), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }


    @Test
    fun checkWinXDiagonal(){
        interactor.matrix.setMark(Point(0, 0), Mark.X.value)
        interactor.matrix.setMark(Point(1, 1), Mark.X.value)
        interactor.matrix.setMark(Point(2, 2), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }

    @Test
    fun checkWinXReverseDiagonal(){
        interactor.matrix.setMark(Point(0, 2), Mark.X.value)
        interactor.matrix.setMark(Point(1, 1), Mark.X.value)
        interactor.matrix.setMark(Point(2, 0), Mark.X.value)
        assertEquals(interactor.checkWin(), Mark.X.value)
    }
}