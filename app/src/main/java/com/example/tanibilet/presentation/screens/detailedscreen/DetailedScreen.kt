package com.example.tanibilet.presentation.screens.detailedscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Airlines
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.HourglassFull
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KingBed
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetailedScreen(
    navController: NavController,
    link: String,
    viewModel: DetailedViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(Unit) {
        viewModel.onEvent(DetailedScreenEvent.TakeInformationAboutTour(link))
    }
    if (state.isLoading) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "Details",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(
                    onClick = { /*Adding to Favorite in Room DB*/ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    } else {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "Details",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge,
                )
                IconButton(
                    onClick = { viewModel.onEvent(DetailedScreenEvent.ChangeFavorite(!state.isFavorite)) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = if (state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = ("Data publikacji:" + state.informatioAboutTour?.publicationDate) ?: "",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = 8.dp, start = 8.dp, bottom = 6.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state.informatioAboutTour?.destination ?: "",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(start = 10.dp),
                )
                Text(
                    text = state.informatioAboutTour?.price ?: "",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(end = 10.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Dates",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column() {
                    Text(
                        text = "Kiedy?",
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = state.informatioAboutTour?.dates?.replace(" -  ", "-") ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.HourglassEmpty,
                        contentDescription = "Hours",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column() {
                    Text(
                        text = "Na ile?",
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = state.informatioAboutTour?.countDay ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.Paid,
                        contentDescription = "Paid",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Column() {
                    Text(
                        text = "Cena wycieczki?",
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = state.informatioAboutTour?.price ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
            Text(
                text = state.informatioAboutTour?.shortDescription ?: "",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Szczegóły oferty:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(10.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.Airlines,
                        contentDescription = "Track",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Przelot: ", style = MaterialTheme.typography.displayMedium)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier.padding(
                    top = 3.dp,
                    start = 16.dp,
                    end = 5.dp,
                    bottom = 5.dp
                )
            ) {
                for (i in 0..state.informatioAboutTour!!.overflight.size - 1) {
                    Row {
                        Text(
                            text = "- " + state.informatioAboutTour.overflight[i],
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 10.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.KingBed,
                        contentDescription = "Hotel",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Nocleg: ", style = MaterialTheme.typography.displayMedium)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier.padding(
                    top = 3.dp,
                    start = 16.dp,
                    end = 5.dp,
                    bottom = 5.dp
                )
            ) {
                for (i in 0..state.informatioAboutTour!!.accommodation.size - 1) {
                    Row {
                        Text(
                            text = "- " + state.informatioAboutTour.accommodation[i],
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.LocalTaxi,
                        contentDescription = "Transfer",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Transfer: ", style = MaterialTheme.typography.displayMedium)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = ("- " + state.informatioAboutTour?.transfer) ?: "",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 7.dp, start = 20.dp, end = 5.dp, bottom = 10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = state.informatioAboutTour?.longDescription!!.replace("[", "")
                    .replace("]", "") ?: "",
                style = MaterialTheme.typography.displayMedium, modifier = Modifier.padding(16.dp),
            )
            Button(
                onClick = { /*TODO go to internet site*/ },
                colors = buttonColors(backgroundColor =  MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                shape = CircleShape
            ) {
                Text(text = "Kup bilet")
            }
        }
    }
}