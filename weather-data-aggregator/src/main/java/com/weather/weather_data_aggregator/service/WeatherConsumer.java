package com.weather.weather_data_aggregator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weather_data_aggregator.model.WeatherData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WeatherConsumer {

    private final WeatherService weatherService;
    private final ObjectMapper objectMapper;

    public WeatherConsumer(WeatherService weatherService, ObjectMapper objectMapper) {
        this.weatherService = weatherService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "weather_topic", groupId = "weather_group")
    public void consumeWeatherData(ConsumerRecord<String, String> record) {
        try {
            WeatherData weatherData = objectMapper.readValue(record.value(), WeatherData.class);
            // weatherService.saveWeatherData(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}