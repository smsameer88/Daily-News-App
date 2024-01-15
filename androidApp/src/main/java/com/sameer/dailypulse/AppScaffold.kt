package com.sameer.dailypulse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sameer.dailypulse.screens.AboutScreen
import com.sameer.dailypulse.screens.ArticlesScreen
import com.sameer.dailypulse.screens.Screens
import com.sameer.dailypulse.screens.SourcesScreen


@Composable
fun AppScaffold(
) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier
    ) {
        composable(route = Screens.ARTICLES.route) {
            ArticlesScreen(
                onAboutButtonClick = {
                    navController.navigate(Screens.ABOUT_DEVICE.route)
                },
                onListButtonClick = {
                    navController.navigate(Screens.SOURCES.route)
                }
            )
        }
        composable(route = Screens.ABOUT_DEVICE.route) {
            AboutScreen {
                navController.popBackStack()
            }
        }
        composable(route = Screens.SOURCES.route) {
            SourcesScreen {
                navController.popBackStack()
            }
        }
    }
}
