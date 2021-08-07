package navigation.screens

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorXmlResource
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
import realize.User
import javax.swing.BoxLayout


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
    private val sizeMatrix = interactor.getSizeMatrix()
    var listState = listOf<List<MutableState<Mark>>>()
    private var isFirstPlayerMove = mutableStateOf(true)

    init {
        listState = List(sizeMatrix.height) { List(sizeMatrix.height) { mutableStateOf(Mark.NOTHING) } }
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
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.weight(1f).fillMaxHeight().background(Color(0xFF89C6EF))) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.weight(1f).padding(10.dp)
                                .border(
                                    BorderStroke(
                                        5.dp,
                                        if (!isFirstPlayerMove.value) Color.White else Color.Unspecified
                                    )
                                )
                        ) {
                            Image(
                                bitmap = User.getIcon(),
                                contentDescription = "",
                                modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(15.dp)
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.weight(1f).padding(10.dp)
                                .border(
                                    BorderStroke(
                                        5.dp,
                                        if (isFirstPlayerMove.value) Color.White else Color.Unspecified
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
                                if (interactor.stateWin.value == "") {
                                    connect()
                                    if (isFirstPlayerMove.value) {
                                        if (interactor.playerTurn(firstPlayer, i, j)) {
                                            isFirstPlayerMove.value = !isFirstPlayerMove.value
                                            if (interactor.stateWin.value != "")
                                                onGoToFinish(interactor.stateWin.value)
                                        }
                                    } else {
                                        if (interactor.playerTurn(secondPlayer, i, j)) {
                                            isFirstPlayerMove.value = !isFirstPlayerMove.value
                                            if (interactor.stateWin.value != "")
                                                onGoToFinish(interactor.stateWin.value)
                                        }
                                    }

                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            val modifier = Modifier.padding(5.dp)
                            when (listState[j][i].value) {
                                Mark.NOTHING -> {
                                }
                                Mark.X ->
                                    Image(
                                        bitmap = imageResource("drawable/krest.png"),
                                        contentDescription = "",
                                        modifier = modifier
                                    )
                                Mark.Y ->
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