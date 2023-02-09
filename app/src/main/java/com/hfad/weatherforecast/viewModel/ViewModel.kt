package com.hfad.weatherforecast.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.weatherforecast.Repository.Repository
import com.hfad.weatherforecast.model.ForecastWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel

class ViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val mResponse : MutableLiveData<Response<ForecastWeather>> = MutableLiveData()

    fun getForecast(key:String, city:String, days: Int){
        viewModelScope.launch {
            try {
                val response = repository.getForecast(key, city, days)

                mResponse.value = response
            }
            catch(e: Exception){
                Log.d("error message",e.toString())
            }
        }
    }
}