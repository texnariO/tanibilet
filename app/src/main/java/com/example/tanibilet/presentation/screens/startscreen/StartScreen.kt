package com.example.tanibilet.presentation.screens.startscreen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import android.content.Context

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val state = viewModel.state
    Scaffold(
        bottomBar = {
            BottomBarNavigation(selectedTab = selectedTab) { tab ->
                selectedTab = tab
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(1) {
                ShimmerListItem(
                    isLoading = state.value.isLoading, contentAffterLoading = {
                        if (selectedTab == 0) {
                            viewModel.onEvent(StartScreenEvent.TakeInformationAboutAllTours)
                            for (tourInfo in state.value.listOffer!!) {
                                TourCard(tourInfo = tourInfo, navController)
                            }
                        }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

    }


}

@Composable
fun BottomBarNavigation(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.tertiary) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home page"
                )
            },
            label = { Text(text = "Wszystkie") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.background)
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite List"
                )
            },
            label = { Text(text = "Ulubione") },
            colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.background)
        )
    }
}