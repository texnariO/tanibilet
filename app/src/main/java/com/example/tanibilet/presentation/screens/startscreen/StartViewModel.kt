package com.example.tanibilet.presentation.screens.startscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanibilet.di.data.AppModule
import com.example.tanibilet.presentation.utils.classes.TourShortInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import retrofit2.HttpException
import java.io.Console
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(): ViewModel(){
        private val _state = mutableStateOf(StartState())
        val state: State<StartState> =_state
        val databaseApi = AppModule.provideDatabaseApi()

        init {
            _state.value = _state.value.copy(
                    isLoading = true
            )
            viewModelScope.launch {
                        try {
                            var listsTourInfo: List<TourShortInfo> = emptyList<TourShortInfo>()
                            for (i in 1..10){
                                val response = databaseApi.getListInfoAboutTour(i.toString())
                                if(response.isSuccessful) {

                                    Log.e("userviewmodel",response.body()?.let { Jsoup.parse(it) }.toString())
                                    val document = response.body()?.let { Jsoup.parse(it) }
                                    val tourElements: List<Element> =
                                        (document?.select("a.elementor-element-b5fa81e")
                                            ?: return@launch) as List<Element>
                                    var listToursInfo = tourElements.map { element ->
                                        val link = element.attr("href")
                                        val price =
                                            element.selectFirst("h2.elementor-heading-title")
                                                ?.text() ?: ""
                                        val place =
                                            element.selectFirst("h3.elementor-heading-title")
                                                ?.text() ?: ""
                                        val date =
                                            element.select("h4.elementor-heading-title")[1]?.text()
                                                ?: ""
                                        val description = element.selectFirst("p")?.text() ?: ""
                                        val publicationDate =
                                            element.select("h4.elementor-heading-title")[3]?.text()
                                                ?: ""
                                        val imageSrscset = element.selectFirst("img")?.attr("data-srcset") ?: ""
                                        val imageUrl  = imageSrscset.split(",")
                                            .map { it.trim() }
                                            .minByOrNull {
                                                val size = it.split(" ")[1].removeSuffix("w").toInt()
                                                size
                                            }?.split(" ")?.get(0) ?: ""
                                        Log.e("Image src",imageUrl.toString())
                                        TourShortInfo(
                                            link = link,
                                            price = price,
                                            place = place,
                                            date = date,
                                            description = description,
                                            publicationDate = publicationDate,
                                            imageSrc = imageUrl
                                        )
                                    }
                                    listsTourInfo += listToursInfo
                                }
                                }

                            Log.e("userviewmodellength",listsTourInfo.size.toString())
                            _state.value = _state.value.copy(
                                listOffer = listsTourInfo,
                                isLoading = false
                            )

                        }catch (e: IOException) {
                                Log.e("UserViewModel", "Network error", e)
                        } catch (e: HttpException) {
                                Log.e("UserViewModel", "Server error", e)
                        }
                }
        }
}