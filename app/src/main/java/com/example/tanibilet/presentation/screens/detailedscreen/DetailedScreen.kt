package com.example.tanibilet.presentation.screens.detailedscreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetailedScreen(
    navController: NavController,
    link: String,
    viewModel: DetailedViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        viewModel.onEvent(DetailedScreenEvent.TakeInformationAboutTour(link))
    }
    if(viewModel.state.value.isLoading){
        Text(text = link)
    }else{
        Text(text = "Loading Finish")
    }
}