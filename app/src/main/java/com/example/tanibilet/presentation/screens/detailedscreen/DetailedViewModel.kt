package com.example.tanibilet.presentation.screens.detailedscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tanibilet.di.data.AppModule
import javax.inject.Inject

class DetailedViewModel @Inject constructor(): ViewModel(){
    private val _state = mutableStateOf(DetailedState())
    val state: State<DetailedState> = _state
    val databaseApi = AppModule.provideDatabaseApi()

    fun onEvent(event: DetailedScreenEvent){
        when(event){
            is DetailedScreenEvent.TakeInformationAboutTour ->{
                _state.value = _state.value.copy(
                    isLoading = true
                )
            }
        }

    }

}