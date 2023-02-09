package com.hfad.weatherforecast.Repository

import com.hfad.weatherforecast.model.ForecastWeather
import com.hfad.weatherforecast.network.RetrofitInstance
import com.hfad.weatherforecast.network.WeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val weatherApi: WeatherApi) {
    suspend fun getForecast(key: String, city: String, days: Int) : Response<ForecastWeather> {
        return weatherApi.getForecast(key,city,days)
    }
}