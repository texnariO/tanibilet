package com.example.tanibilet.di.data

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY) // Логувати всі запити і відповіді
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun provideDatabaseApi(): DatabaseApi{
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(DatabaseApi.BASE_URL)
            .client(client)
            .build()
            .create(DatabaseApi::class.java)
    }


}