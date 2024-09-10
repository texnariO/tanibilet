package com.example.tanibilet.presentation.utils.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tanibilet.presentation.screens.detailedscreen.DetailedScreen
import com.example.tanibilet.presentation.screens.startscreen.StartScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.StartScreen.route){
        composable(Routes.StartScreen.route){
            StartScreen(navController = navController)
        }
        composable(Routes.DetailsScreen.route, arguments = listOf(
            navArgument(name = "link"){
                type = NavType.StringType
            }
        )){
            it.arguments?.getString("link")
                ?.let { it -> DetailedScreen(navController = navController,link = it) }
        }
    }
}