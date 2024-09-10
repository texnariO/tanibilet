package com.example.tanibilet.presentation.screens.detailedscreen

import android.net.wifi.ScanResult.InformationElement
import com.example.tanibilet.presentation.utils.classes.InformationAboutTour

data class DetailedState(
    val isLoading: Boolean = false,
    val informatioAboutTour: InformationAboutTour? = null
)
