package com.example.tanibilet.di.data

import com.example.tanibilet.presentation.utils.Constants.GET_DESCRIPTION_ABOUT_TOUR
import com.example.tanibilet.presentation.utils.Constants.GET_SHORT_INFO_ABOUT_TOUR
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DatabaseApi {

    @GET(GET_SHORT_INFO_ABOUT_TOUR)
    suspend fun getListInfoAboutTour(
        @Query("e-page-65afc52")id: String
    ): Response<String>

    @GET(GET_DESCRIPTION_ABOUT_TOUR)
    suspend fun getDescriptonAboutTour(
        @Path("link") link: String
    ):Response<String>

    companion object{
        const val BASE_URL = "https://tanibilet.eu/"
    }
}