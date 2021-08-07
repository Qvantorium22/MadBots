package navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.pop
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import navigation.screens.FinishScreen
import navigation.screens.SplashScreen
import navigation.screens.StartScreen
import navigation.screens.TestScreen

class NavHostComponent(componentContext: ComponentContext) : NavComponent, ComponentContext by componentContext {
    private val router = router<ScreenConfig, NavComponent>(
        initialConfiguration = ScreenConfig.Splash(),
        childFactory = ::createScreenComponent
    )

    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): NavComponent {
        return when (screenConfig) {
            is ScreenConfig.Splash -> {
                SplashScreen(componentContext, ::onGoToStartMenu)
            }

            is ScreenConfig.Start -> {
                StartScreen(componentContext, ::onGoToTest)
            }

            is ScreenConfig.Test -> {
                TestScreen(componentContext, ::onGoToFinish, ::onGoToBack)
            }

            is ScreenConfig.Finish -> {
                FinishScreen(componentContext, screenConfig.name, ::onGoToBack)
            }
        }
    }

    private fun onGoToStartMenu() {
        router.push(ScreenConfig.Start())
    }

    private fun onGoToTest() {
        router.push(ScreenConfig.Test)
    }

    private fun onGoToBack() {
        router.pop()
    }

    private fun onGoToFinish(name: String) {
        router.push(ScreenConfig.Finish(name))
    }

    private sealed class ScreenConfig() : Parcelable {
        class Splash : ScreenConfig()
        class Start : ScreenConfig()
        object Test : ScreenConfig()
        data class Finish(val name: String) : ScreenConfig()
    }

    @Composable
    override fun render() {
        Children(routerState = router.state) {
            it.instance.render()
        }
    }

}