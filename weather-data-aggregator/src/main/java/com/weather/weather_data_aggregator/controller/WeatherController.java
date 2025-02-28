package com.weather.weather_data_aggregator.controller;

import com.weather.weather_data_aggregator.model.WeatherResponse;
import com.weather.weather_data_aggregator.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/async/{city}")
    public String requestWeather(@PathVariable String city) {
        weatherService.requestWeather(city);
        return "Weather request for " + city + " sent to Kafka!";
    }
}