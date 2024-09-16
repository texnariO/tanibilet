package com.example.tanibilet.presentation.screens.startscreen

import com.example.tanibilet.presentation.utils.classes.TourShortInfo

data class StartState(
    val listOffer: List<TourShortInfo>? = null,
    val isLoading: Boolean = true,
    val listFavoriteOffer: List<TourShortInfo>? = null
)