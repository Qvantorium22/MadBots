package realize

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.imageResource

object User {
    private const val drawablePath = "drawable/"

    var defaultIconPath = drawablePath + "wizard.png"
    var currentIconPath = ""

    @Composable
    fun getIcon() = if (currentIconPath == "") imageResource(defaultIconPath) else imageResource(currentIconPath)
}