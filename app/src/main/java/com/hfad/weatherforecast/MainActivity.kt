package com.hfad.weatherforecast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.weatherforecast.Repository.Repository
import com.hfad.weatherforecast.databinding.ActivityMainBinding
import com.hfad.weatherforecast.databinding.ForecastLayoutBinding
import com.hfad.weatherforecast.model.Condition
import com.hfad.weatherforecast.network.RetrofitInstance
import com.hfad.weatherforecast.utills.Constants.Companion.API_KEY
import com.hfad.weatherforecast.utills.Constants.Companion.DAYS
import com.hfad.weatherforecast.viewModel.ViewModel
import com.hfad.weatherforecast.viewModel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: ViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ForecastLayoutBinding
    @Inject lateinit var repository: Repository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = ForecastLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val mainViewFactory = ViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, mainViewFactory).get(ViewModel::class.java)



        binding.getCurrentWeather.setOnClickListener {
            val city = binding.cityName.text.toString()
            mainViewModel.getForecast(API_KEY, city, DAYS)
            mainViewModel.mResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    setContentView(binding.root)
                    binding.tvDateTime.text =
                        "last_updated: " + response.body()?.current?.last_updated.toString()
                    binding.tvCondition.text =
                         response.body()?.current?.condition?.text.toString()
                    binding.tvCurrentTemp.text =
                        "tempC: " + response.body()?.current?.temp_c.toString()

                } else {
                    Log.d("error", "cant get forecast")
                    Toast.makeText(this, "enter the correct city name", Toast.LENGTH_SHORT).show()
                }

            })
        }


        binding.getForecast.setOnClickListener {
            val city = binding.cityName.text.toString()
            mainViewModel.getForecast(API_KEY, city, DAYS)
            mainViewModel.mResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    setContentView(binding2.root)

                    binding2.tvDate.text =
                        "date: " + response.body()?.forecast?.forecastday?.elementAt(0)?.date.toString()
                    binding2.tvCondition.text =
                        "condition: " + response.body()?.forecast?.forecastday?.elementAt(0)?.day?.condition?.text.toString()
                    binding2.tvWind.text =
                        "maxwind_mph: " + response.body()?.forecast?.forecastday?.elementAt(0)?.day?.maxwind_mph.toString()
                    binding2.tvMaxtempC.text =
                        "maxtemp_c: " + response.body()?.forecast?.forecastday?.elementAt(0)?.day?.maxtemp_c.toString()
                    binding2.tvMintempC.text =
                        "mintemp_c: " + response.body()?.forecast?.forecastday?.elementAt(0)?.day?.mintemp_c.toString()
                    binding2.tvAvgTempC.text =
                        "avgtemp_c: " + response.body()?.forecast?.forecastday?.elementAt(0)?.day?.avgtemp_c.toString()

                    binding2.tvDate1.text =
                        "date: " + response.body()?.forecast?.forecastday?.elementAt(1)?.date.toString()
                    binding2.tvCondition1.text =
                        "condition: " + response.body()?.forecast?.forecastday?.elementAt(1)?.day?.condition?.text.toString()
                    binding2.tvWind1.text =
                        "maxwind_mph: " + response.body()?.forecast?.forecastday?.elementAt(1)?.day?.maxwind_mph.toString()
                    binding2.tvMaxtempC1.text =
                        "maxtemp_c: " + response.body()?.forecast?.forecastday?.elementAt(1)?.day?.maxtemp_c.toString()
                    binding2.tvMintempC1.text =
                        "mintemp_c: " + response.body()?.forecast?.forecastday?.elementAt(1)?.day?.mintemp_c.toString()
                    binding2.tvAvgTempC1.text =
                        "avgtemp_c: " + response.body()?.forecast?.forecastday?.elementAt(1)?.day?.avgtemp_c.toString()

                    binding2.tvDate2.text =
                        "date: " + response.body()?.forecast?.forecastday?.elementAt(2)?.date.toString()
                    binding2.tvCondition2.text =
                        "condition: " + response.body()?.forecast?.forecastday?.elementAt(2)?.day?.condition?.text.toString()
                    binding2.tvWind2.text =
                        "maxwind_mph: " + response.body()?.forecast?.forecastday?.elementAt(2)?.day?.maxwind_mph.toString()
                    binding2.tvMaxtempC2.text =
                        "maxtemp_c: " + response.body()?.forecast?.forecastday?.elementAt(2)?.day?.maxtemp_c.toString()
                    binding2.tvMintempC2.text =
                        "mintemp_c: " + response.body()?.forecast?.forecastday?.elementAt(2)?.day?.mintemp_c.toString()
                    binding2.tvAvgTempC2.text =
                        "avgtemp_c: " + response.body()?.forecast?.forecastday?.elementAt(2)?.day?.avgtemp_c.toString()

                } else {
                    Log.d("error", "cant get forecast")
                    Toast.makeText(this, "enter the correct city name", Toast.LENGTH_SHORT).show()
                }

            })

               }

        }
    @Override
    override fun onBackPressed() {
        setContentView(binding.root)
        onResume()

    }
        }







