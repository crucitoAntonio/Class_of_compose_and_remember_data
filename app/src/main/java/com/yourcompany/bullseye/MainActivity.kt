package com.yourcompany.bullseye

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourcompany.bullseye.screens.AboutScreen
import com.yourcompany.bullseye.screens.GameScreen
import com.yourcompany.bullseye.utils.ConstantsGame

class MainActivity : BaseActivity() {
    @Composable
    override fun MainContent() {
        MainScreen()
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ConstantsGame.GAME_SCREEN) {
            composable(ConstantsGame.GAME_SCREEN) {
                GameScreen(onNavigationToAbout = {
                    navController.navigate(
                        ConstantsGame.ABOUT_SCREEN
                    )
                })
            }
            composable(ConstantsGame.ABOUT_SCREEN) { AboutScreen(backPressed = { navController.navigateUp() }) }
        }
    }
}