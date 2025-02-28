package com.weather.weather_data_aggregator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "weather_requests"; // Kafka topic name

    public WeatherProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWeatherRequest(String city) {
        LOGGER.info("Sending weather request for city: {}", city);
        kafkaTemplate.send(TOPIC, city);
    }
}