package com.github.cxz88.testplugindevkit.tool

/**
 * @author chenxinzhi
 * @date 2024/1/23
 */

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.intellij.openapi.project.Project
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

sealed class Route(val route: String) {
    data object Main : Route("mainRoute")
    data object Add : Route("addRoute")

}

@Composable
fun NavApp(project: Project?) {
    val rememberNavigator = rememberNavigator()

    NavHost(navigator = rememberNavigator,
        initialRoute = Route.Main.route,
        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)).background(color = Color(0xFF2b2d30)).padding(10.dp).padding(horizontal = 15.dp),
        navTransition = remember {
            NavTransition(createTransition = slideInHorizontally(initialOffsetX = {
                //入场
                it
            }), destroyTransition = slideOutHorizontally(targetOffsetX = {
                it
            }), resumeTransition = slideInHorizontally(initialOffsetX = {
                //入场
                -it
            }), pauseTransition = slideOutHorizontally(targetOffsetX = {
                -it
            }))
        }) {
        scene(Route.Main.route) {
            App(project) {
                rememberNavigator.navigate(Route.Add.route)
            }
        }
        scene(Route.Add.route) {
            Add(project) {
                rememberNavigator.popBackStack()
            }
        }
    }
}



