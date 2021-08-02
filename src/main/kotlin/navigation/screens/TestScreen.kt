package navigation.screens

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import core.Matrix
import core.Point
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import navigation.NavComponent
import realize.Mark
import realize.TTTEInteractor
import realize.UIPlayer


class TestScreen(
    private val componentContext: ComponentContext,
    private val onGoToFinish: (name: String) -> Unit
) : NavComponent, ComponentContext by componentContext {
    var stateWin = mutableStateOf("")

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun render() {
        val firstPlayer = UIPlayer(Mark.X)
        val secondPlayer = UIPlayer(Mark.Y)
        val interactor = TTTEInteractor(
            firstPlayer,
            secondPlayer
        )
        stateWin = interactor.stateWin
        GlobalScope.launch {
            interactor.connect()
        }
        MaterialTheme {
            DesktopTheme {
                var isFirstPlayerMove = true
                Column {
                    for (j in interactor.listState.indices) {
                        Row {
                            for (i in interactor.listState[j].indices) {
                                Card(
                                    modifier = Modifier.height(52.dp)
                                        .width(100.dp)
                                        .padding(start = 10.dp, bottom = 10.dp)
                                        .align(Alignment.Top),
                                    onClick = {
                                        if (interactor.stateWin.value == "") {
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
                                        text = interactor.listState[j][i].value.toString(),
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }

                    Row { Text(if (interactor.stateWin.value == "") interactor.stateWin.value else interactor.stateWin.value) }
                }
            }
        }
    }
}