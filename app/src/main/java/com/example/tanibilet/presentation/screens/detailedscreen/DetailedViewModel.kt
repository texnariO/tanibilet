package com.example.tanibilet.presentation.screens.detailedscreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanibilet.di.data.AppModule
import com.example.tanibilet.presentation.utils.classes.InformationAboutTour
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import org.jsoup.Jsoup
import org.jsoup.select.Evaluator.IsFirstChild
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class DetailedViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(DetailedState())
    val state: State<DetailedState> = _state
    val databaseApi = AppModule.provideDatabaseApi()

    fun onEvent(event: DetailedScreenEvent) {
        when (event) {
            is DetailedScreenEvent.TakeInformationAboutTour -> {
                downloadInformatioAboutOffer(event.link)
            }
            is DetailedScreenEvent.ChangeFavorite ->{
                changeFavorite(event.isFavorite)
            }
        }
    }
    fun changeFavorite(isFavoriteNew: Boolean){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isFavorite = isFavoriteNew
            )
        }
    }



    fun downloadInformatioAboutOffer(link: String) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isLoading = true
                )
                val response = databaseApi.getDescriptonAboutTour(link)
                if (response.isSuccessful) {
                    Log.e("Check", "Response Success")
                    val document = response.body()?.let { Jsoup.parse(it) } ?: return@launch
                    val publicationDate =
                        document.select("h4.elementor-heading-title.elementor-size-default")[1]?.text()
                            ?: ""
                    val destination =
                        document.select("h2.product_title.entry-title.elementor-heading-title.elementor-size-default")
                            .text() ?: ""
                    val price =
                        document.select("h2.elementor-heading-title.elementor-size-default")[2].text()
                            ?: ""
                    val dateFirst = document.select("p.elementor-heading-title.elementor-size-default")[0].text()
                            ?: ""
                    val dateSecond = document.select("p.elementor-heading-title.elementor-size-default")[1].text()
                        ?: ""
                    val dates = dateFirst + dateSecond
                    val countDay = document.select("p.elementor-icon-box-description")[0].text() ?: ""
                    val shortDescription = document.select("div.article-content")[0].text() ?: ""
                    val article = document.select("article")
                    val overflight= article.select("ul")[0]
                    val accommodation = article.select("ul")[1]
                    val transfer = article.select("ul")[2].text()?:""
                    val selectAllLiAccommodation = accommodation.select("li")
                    val selectAllLi = overflight.select("li")
                    val selectAllP = article.select("p")
                    var listAccommodation: List<String> = emptyList<String>()
                    var listOverflight: List<String> = emptyList<String>()
                    var listDescription: List<String>  = emptyList<String>()
                    for(i in 0..selectAllLiAccommodation.size-1){
                        listAccommodation += selectAllLiAccommodation[i].text()
                    }
                    for(i in 0..<selectAllLi.size){
                        listOverflight+= selectAllLi[i].text()
                    }
                    for(i in selectAllP.size.minus(selectAllP.size.minus(4))..selectAllP.size.minus(1)){
                        listDescription+= selectAllP[i].text()
                    }
                    val longDescription = listDescription.toString()
                    Log.e("Check", listAccommodation.toString())
                    val infoTour = InformationAboutTour(
                        publicationDate,
                        destination,
                        price,
                        dates,
                        countDay,
                        shortDescription,
                        listOverflight,
                        listAccommodation,
                        transfer,
                        longDescription
                    )
                    _state.value = _state.value.copy(
                        isLoading = false,
                        informatioAboutTour = infoTour
                    )
                }
            } catch (e: IOException) {
                Timber.tag("UserViewModel").e(e, "Network error")
            } catch (e: HttpException) {
                Timber.tag("UserViewModel").e(e, "Server error")
            }
        }
    }

}