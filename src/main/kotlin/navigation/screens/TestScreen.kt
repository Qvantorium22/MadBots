package navigation.screens

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import core.MadInteractor
import core.Matrix
import core.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import navigation.NavComponent
import realize.Mark
import realize.MarkTransform.transformToMark
import realize.TTTEInteractor
import realize.UIPlayer


class TestScreen(
    private val componentContext: ComponentContext,
    private val onGoToFinish: (name: String) -> Unit
) : NavComponent, ComponentContext by componentContext {
    var stateWin = mutableStateOf("")

    val firstPlayer = UIPlayer(Mark.X)
    val secondPlayer = UIPlayer(Mark.Y)
    val interactor = TTTEInteractor(
        firstPlayer,
        secondPlayer
    )
    val sizeMatrix = interactor.getSizeMatrix()
    var listState = listOf<List<MutableState<Mark>>>()
    init {
        listState = List(sizeMatrix.height){List(sizeMatrix.height){ mutableStateOf(Mark.NOTHING)}}
        GlobalScope.launch(Dispatchers.Main) {
            connect()
        }
        stateWin = interactor.stateWin
    }

    private fun connect() {
        GlobalScope.launch(Dispatchers.Main) {
            interactor.getDataFlow().collect { row ->
                if (row != null) {
                    for (y in row.indices)
                        for (x in row[y].indices)
                            listState[x][y].value = (transformToMark(row[x][y]) as Mark)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun render() {
        MaterialTheme {
            DesktopTheme {
                var isFirstPlayerMove = true
                Column {
                    for (j in listState.indices) {
                        Row {
                            for (i in listState[j].indices) {
                                Card(
                                    modifier = Modifier.height(52.dp)
                                        .width(100.dp)
                                        .padding(start = 10.dp, bottom = 10.dp)
                                        .align(Alignment.Top),
                                    onClick = {
                                        if (interactor.stateWin.value == "") {
                                            connect()
                                            if (isFirstPlayerMove) {
                                                interactor.playerTurn(firstPlayer, i, j)
                                                if (interactor.stateWin.value != "")
                                                    onGoToFinish(interactor.stateWin.value)
                                            } else {
                                                interactor.playerTurn(secondPlayer, i, j)
                                                if (interactor.stateWin.value != "")
                                                    onGoToFinish(interactor.stateWin.value)
                                            }
                                            isFirstPlayerMove = !isFirstPlayerMove
                                        }
                                    }
                                ) {
                                    Text(
                                        text = listState[j][i].value.sym.toString(),
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }

                    Row { Text(if (interactor.stateWin.value == "") interactor.stateWin.value else interactor.stateWin.value) }
                    Button(onClick = {
                        interactor.matrix.clearData()
                        stateWin.value = ""
//                        connect()
                    }){
                        Text("Обновить")
                    }
                }
            }
        }
    }
}