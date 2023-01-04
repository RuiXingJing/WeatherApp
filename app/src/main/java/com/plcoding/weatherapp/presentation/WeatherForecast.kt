package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherForecast() {
    val weatherData = WeatherData(
        time = LocalDateTime.now(),
        temperatureCelsius = 9.1,
        pressure = 1021.9,
        windSpeed = 13.6,
        humidity = 80.0,
        weatherType = WeatherType.Foggy
    )
    val listWeatherData = listOf<WeatherData>(
        weatherData,
        weatherData.copy(
            temperatureCelsius = 8.1,
            weatherType = WeatherType.DenseDrizzle
        ),
        weatherData.copy(
            temperatureCelsius = 8.8,
            weatherType = WeatherType.HeavyRain
        )
    )
    val weatherState = WeatherState(
        weatherInfo = WeatherInfo(
            weatherDataPerDay = mutableMapOf<Int, List<WeatherData>>().apply {
                this[0] = listWeatherData
            },
            currentWeatherData = null
        )
    )

    WeatherAppTheme {
        WeatherForecast(weatherState)
    }
}