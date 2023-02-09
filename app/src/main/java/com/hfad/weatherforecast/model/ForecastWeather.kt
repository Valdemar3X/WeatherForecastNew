package com.hfad.weatherforecast.model

data class ForecastWeather(
    val alerts: Alerts,
    val current: Current,
    val forecast: Forecast,
    val location: Location
)