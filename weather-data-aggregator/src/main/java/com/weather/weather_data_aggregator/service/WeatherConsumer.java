package com.weather.weather_data_aggregator.service;

import com.weather.weather_data_aggregator.model.WeatherResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherConsumer {
    private final WeatherService weatherService;

    public WeatherConsumer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @KafkaListener(topics = "weather_requests", groupId = "weather-group")
    public void processWeatherRequest(String city) {
        System.out.println("Processing weather request for city: " + city);
        String response = weatherService.getWeatherFromOpenWeather(city);
        System.out.println("Fetched weather for " + city + ": " + response);
    }

}
