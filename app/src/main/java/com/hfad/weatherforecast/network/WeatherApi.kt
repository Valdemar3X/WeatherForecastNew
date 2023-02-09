package com.hfad.weatherforecast.network

import com.hfad.weatherforecast.model.ForecastWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")


    suspend fun getForecast(
        @Query("key") key:String,
        @Query("q") city:String,
        @Query("days") days: Int

    ): Response<ForecastWeather>
}