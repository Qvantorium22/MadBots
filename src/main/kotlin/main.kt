import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import core.Matrix
import core.Point
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import navigation.NavHostComponent
import realize.Mark
import realize.TTTEInteractor
import realize.UIPlayer

@OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
private fun NewsStory(interactor: TTTEInteractor, firstPlayer: UIPlayer, secondPlayer: UIPlayer) {
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
                                        } else
                                            interactor.playerTurn(secondPlayer, i, j)
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

                Row { Text(interactor.stateWin.value) }
            }
        }
    }
}

fun main() = Window(size = IntSize(900, 600), resizable = false) {
    DecomposeDesktopExampleTheme {
        rememberRootComponent(factory = ::NavHostComponent).render()
    }
}

@Composable
fun DecomposeDesktopExampleTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColors,
        typography = Typography(
            defaultFontFamily = FontFamily(Font("google_sans_regular.ttf"))
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}
val primary = Color(48, 163, 230)
val secondary = Color(24, 25, 29)

private val DarkColors = darkColors(
    primary = primary,
    secondary = secondary,
    surface = secondary,
    onPrimary = Color.White,
    onSecondary = Color.White
)
