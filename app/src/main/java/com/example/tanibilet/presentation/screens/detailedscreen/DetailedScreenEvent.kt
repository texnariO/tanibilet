package com.example.tanibilet.presentation.screens.detailedscreen

sealed class DetailedScreenEvent {
    data class TakeInformationAboutTour(val link: String): DetailedScreenEvent()
    data class ChangeFavorite(val isFavorite: Boolean): DetailedScreenEvent()
}