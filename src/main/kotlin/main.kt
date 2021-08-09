import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.IntSize
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import tictactoe.navigation.NavHostComponent

fun main() = Window(size = IntSize(900, 600), resizable = true, title = "MadBots") {
    MaterialTheme {
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
