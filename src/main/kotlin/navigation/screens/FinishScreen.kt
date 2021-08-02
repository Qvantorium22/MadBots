package navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import navigation.NavComponent

class FinishScreen(
    private val componentContext: ComponentContext,
    private val name: String,
    private val onGoToBack: () -> Unit
): NavComponent, ComponentContext by componentContext {

    @Composable
    override fun render() {
        createFinishScreen(name, onGoToBack)
    }
    @Composable
    fun createFinishScreen(
        greeting: String,
        onGoBackClicked: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Greeting
            Text(
                text = greeting,
                fontSize = 40.sp
            )

            // Spacing between text and button
            Spacer(modifier = Modifier.height(30.dp))

            // Go back button
            Button(onClick = onGoBackClicked) {
                Text(text = "GO BACK!")
            }
        }
    }
}