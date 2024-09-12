package com.example.tanibilet.presentation.utils.classes

data class InformationAboutTour(
    val publicationDate: String,
    val destination: String,
    val price: String,
    val dates: String,
    val countDay: String,
    val shortDescription: String,
    val overflight: List<String>,
    val accommodation: List<String>,
    val transfer: String,
    val longDescription: String
)
