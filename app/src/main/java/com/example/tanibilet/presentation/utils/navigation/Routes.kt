package com.example.tanibilet.presentation.utils.navigation

sealed class Routes (val route: String){
    object StartScreen: Routes(route="start_screen")
    object DetailsScreen: Routes(route="details_screen/{link}")
}