package com.example.tanibilet.presentation.screens.startscreen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tanibilet.presentation.screens.startscreen.elements.ShimmerListItem
import com.example.tanibilet.presentation.utils.classes.ui.TourCard

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel = hiltViewModel()
) {
    var isAtEnd by remember { mutableStateOf(false)}

    val scrollState = rememberScrollState()
    val state = viewModel.state
    LazyColumn(modifier = Modifier.fillMaxSize(), userScrollEnabled = !isAtEnd){
        items(66){
            ShimmerListItem(isLoading = state.value.isLoading, contentAffterLoading = { for(tourInfo in state.value.listOffer!!){
                TourCard(tourInfo = tourInfo, navController)
            } }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
        }
    }
    LaunchedEffect(scrollState.value){
        Log.e("Lazy Column",scrollState.maxValue.toString())
        val maxScroll = scrollState.maxValue
        isAtEnd = scrollState.value >= maxScroll
    }
}