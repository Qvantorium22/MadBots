package tictactoe.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.arkivanov.decompose.ComponentContext
import tictactoe.navigation.NavComponent
import java.util.*
import kotlin.concurrent.fixedRateTimer

class SplashScreen(
    private val componentContext: ComponentContext,
    private val goToStart: () -> Unit
) : NavComponent, ComponentContext by componentContext {
    private val timer = Timer()
    private var state = mutableStateOf(0f)

    @Composable
    override fun render() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                goToStart()
            }
        }, 2000)

        fixedRateTimer("", false, 1000L, 100L) {
            val value = state.value + (100.0 / 1000.0).toFloat()
            if (value >= 1f) {
                state.value = value.toInt().toFloat()
                this.cancel()
            } else {
                state.value = value
            }
        }

        Image(
            bitmap = imageResource("drawable/Rectangle16.png"),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item { SplashText() }
        }
    }

    @OptIn(ExperimentalUnitApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
    @Composable
    fun SplashText() {
        MaterialTheme {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(Color(0xFF89C6EF))
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
            ) {
                Image(
                    bitmap = imageResource("drawable/delete.png"),
                    contentDescription = "",
                    modifier = Modifier.weight(1f)
                        .padding(start = 25.dp, top = 25.dp, bottom = 25.dp)
                        .aspectRatio(1f),
                    alpha = state.value
                )

                Text(
                    "TIC TAC TOE",
                    color = Color.Black,
                    fontSize = TextUnit(72f, TextUnitType.Sp),
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(6f)
                        .padding(top = 25.dp, bottom = 25.dp)

                )

                Image(
                    bitmap = imageResource("drawable/delete.png"),
                    contentDescription = "",
                    modifier = Modifier.weight(1f)
                        .padding(end = 25.dp, top = 25.dp, bottom = 25.dp)
                        .aspectRatio(1f),
                    alpha = state.value
                )

            }
        }
    }
}