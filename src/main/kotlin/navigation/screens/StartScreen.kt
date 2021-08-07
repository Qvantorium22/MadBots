package navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import navigation.NavComponent

class StartScreen(
    private val componentContext: ComponentContext,
    private val GoToStartMenu: () -> Unit
) : NavComponent, ComponentContext by componentContext {

    @Composable
    override fun render() {
        createStartMenu()
    }


    @OptIn(ExperimentalUnitApi::class)
    @Composable
    private fun createStartMenu() {
        Image(
            bitmap = imageResource("drawable/Rectangle16.png"),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(modifier = Modifier.padding(all = 100.dp)) {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(
                        Color.White
                    ).fillMaxSize()
                ) {
                    Text(
                        "TIC TAC TOE",
                        color = Color.Black,
                        fontSize = TextUnit(72f, TextUnitType.Sp),
                        fontStyle = FontStyle.Normal
                    )
                    Row(modifier = Modifier.padding(start = 40.dp, bottom = 40.dp, end = 40.dp)) {
                        Box(modifier = Modifier.fillMaxWidth(0.33f).aspectRatio(1f)) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )

                            Image(
                                bitmap = imageResource("drawable/pencil.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(0.25f).align(Alignment.BottomEnd)
                            )
                        }
                        Column(modifier = Modifier.padding(start = 30.dp)) {
                            Button(
                                onClick = { GoToStartMenu(); println("qu qu") },
                                modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF89C6EF))
                            ) {
                                Image(
                                    bitmap = imageResource("drawable/play_button.png"),
                                    contentDescription = "",
                                    modifier = Modifier.aspectRatio(1f).weight(1f).padding(all = 5.dp),
                                )

                                Text(
                                    "Начать игру",
                                    color = Color.Black,
                                    fontSize = TextUnit(30f, TextUnitType.Sp),
                                    fontStyle = FontStyle.Normal,
                                    modifier = Modifier.weight(4f)
                                )
                            }

                            Button(
                                onClick = {},
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF89C6EF))
                            ) {
                                Image(
                                    bitmap = imageResource("drawable/brain.png"),
                                    contentDescription = "",
                                    modifier = Modifier.aspectRatio(1f).weight(1f).padding(all = 5.dp)
                                )

                                Text(
                                    "Обучить бота",
                                    color = Color.Black,
                                    fontSize = TextUnit(30f, TextUnitType.Sp),
                                    fontStyle = FontStyle.Normal,
                                    modifier = Modifier.weight(4f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}