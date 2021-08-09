package navigation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import navigation.NavComponent
import realize.Mark
import realize.TTTEInteractor
import realize.UIPlayer
import realize.User

class TestScreen(
    private val componentContext: ComponentContext,
    private val onGoToFinish: (name: String) -> Unit,
    private val onGoToBack: () -> Unit
) : NavComponent, ComponentContext by componentContext {
    var stateWin = mutableStateOf("")

    private val firstPlayer = UIPlayer(Mark.X)
    private val secondPlayer = UIPlayer(Mark.Y)
    private val interactor = TTTEInteractor(
        firstPlayer,
        secondPlayer
    )
    var listState = arrayListOf<ArrayList<MutableState<Int>>>()
    private var isFirstPlayerMove = mutableStateOf(true)
    private val whoWin = mutableStateOf(0)

    init {
        listState = interactor.getDataFlow().value!!
        stateWin = interactor.stateWin
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun render() {
        MaterialTheme {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.weight(1f).fillMaxHeight().background(Color(0xFF89C6EF))) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.weight(1f).padding(10.dp)
                                .border(
                                    BorderStroke(
                                        5.dp,
                                        if (whoWin.value == 2) Color.Green else if (!isFirstPlayerMove.value && whoWin.value == 0) Color.White else Color.Unspecified
                                    )
                                )
                        ) {
                            Image(
                                bitmap = User.getIcon(),
                                contentDescription = "",
                                modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(15.dp)
                            )
                        }
                        Box(modifier = Modifier.weight(1f).padding(10.dp), contentAlignment = Alignment.Center) {
                            Button(onClick = {
                                interactor.clearData(); stateWin.value = ""; isFirstPlayerMove.value = true
                                whoWin.value = 0
                                interactor.restart()
                            }) {}
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.weight(1f).padding(10.dp)
                                .border(
                                    BorderStroke(
                                        5.dp,
                                        if (whoWin.value == 1) Color.Green else if (isFirstPlayerMove.value && whoWin.value == 0) Color.White else Color.Unspecified
                                    )
                                )
                        ) {
                            Image(
                                bitmap = User.getIcon(),
                                contentDescription = "",
                                modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(15.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier.weight(3.45f).fillMaxHeight()) {
                        Image(
                            bitmap = imageResource("drawable/Rectangle16.png"),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier.fillMaxSize(0.8f).background(Color(0xFFFFFFFF))
                                .align(Alignment.Center)
                                .padding(20.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0070BA))) {
                                gameField()
                            }
                        }
                    }
                }
            }
//            TopAppBar(
//                modifier = Modifier.height(35.dp),
//                contentColor = Color.Unspecified,
//                backgroundColor = Color.Unspecified,
//                elevation = 0.dp
//            ) {
//                Button(
//                    onClick = { onGoToBack() },
//                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
//                    elevation = ButtonDefaults.elevation(0.dp, 0.dp)
//                ) {
//                    Image(
//                        bitmap = imageResource("drawable/back.png"),
//                        contentDescription = "",
//                        modifier = Modifier.fillMaxHeight()
//                    )
//                }
//            }
        }
    }

    @Composable
    private fun gameField() {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 5.dp, end = 5.dp)) {
            for (j in listState.indices) {
                Row(modifier = Modifier.weight(1f).padding(top = 5.dp)) {
                    for (i in listState[j].indices) {
                        Button(
                            modifier = Modifier.fillMaxHeight()
                                .weight(1f)
                                .padding(start = 5.dp),
                            onClick = {
                                if (stateWin.value == "") {
                                    if (isFirstPlayerMove.value) {
                                        if (interactor.playerTurn(firstPlayer, i, j)) {
                                            isFirstPlayerMove.value = !isFirstPlayerMove.value
                                            if (stateWin.value != "")
                                                when (stateWin.value) {
                                                    "win1" -> whoWin.value = 1
                                                    "win2" -> whoWin.value = 2
                                                }
//                                                onGoToFinish(stateWin.value)
                                        }
                                    } else {
                                        if (interactor.playerTurn(secondPlayer, i, j)) {
                                            isFirstPlayerMove.value = !isFirstPlayerMove.value
                                            if (interactor.stateWin.value != "")
                                                when (stateWin.value) {
                                                    "win1" -> whoWin.value = 1
                                                    "win2" -> whoWin.value = 2
                                                }
//                                                onGoToFinish(stateWin.value)
                                        }
                                    }

                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            val modifier = Modifier.padding(5.dp)
                            when (listState[j][i].value) {
                                Mark.NOTHING.value -> {
                                }
                                Mark.X.value ->
                                    Image(
                                        bitmap = imageResource("drawable/delete.png"),
                                        contentDescription = "",
                                        modifier = modifier
                                    )
                                Mark.Y.value ->
                                    Image(
                                        bitmap = imageResource("drawable/nolik.png"),
                                        contentDescription = "",
                                        modifier = modifier
                                    )
                            }
                        }
                    }
                }
            }

//                                Row { Text(if (interactor.stateWin.value == "") interactor.stateWin.value else interactor.stateWin.value) }
//                                Button(onClick = {
//                                    interactor.matrix.clearData()
//                                    stateWin.value = ""
//                                }) {
//                                    Text("Обновить")
//                                }
        }
    }
}