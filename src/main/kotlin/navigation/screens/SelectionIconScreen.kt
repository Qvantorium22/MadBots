package navigation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import navigation.NavComponent

class SelectionIconScreen(
    private val componentContext: ComponentContext,
    private val GoToStartMenu: () -> Unit
) : NavComponent, ComponentContext by componentContext {

    @Composable
    override fun render() {
        createSelectionMenu()
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    private fun createSelectionMenu() {
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
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .padding(24.dp, 24.dp, 24.dp, 0.dp)
                    ) {
                        Box(modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                            .padding(0.dp, 0.dp, 8.dp, 0.dp)
                            .border(2.dp, Color(0xFF89C6EF))) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                            .padding(4.dp, 0.dp, 4.dp, 0.dp)
                            .border(2.dp, Color(0xFF89C6EF))) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                            .padding(8.dp, 0.dp, 0.dp, 0.dp)
                            .border(2.dp, Color(0xFF89C6EF))) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .padding(24.dp, 24.dp, 24.dp, 0.dp)
                    ) {
                        Box(modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                            .padding(0.dp, 0.dp, 8.dp, 0.dp)
                            .border(2.dp, Color(0xFF89C6EF))) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                            .padding(4.dp, 0.dp, 4.dp, 0.dp)
                            .border(2.dp, Color(0xFF89C6EF))) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Box(modifier = Modifier
                            .weight(0.5f)
                            .aspectRatio(1f)
                            .padding(8.dp, 0.dp, 0.dp, 0.dp)
                            .border(2.dp, Color(0xFF89C6EF))) {
                            Image(
                                bitmap = imageResource("drawable/wizard.png"),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}