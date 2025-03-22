package com.weather.weather_data_aggregator.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {


    // It is used in a Kafka producer service to send messages to Kafka topics.
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Kafka topic name
    private static final String TOPIC = "weather_requests";

    public WeatherProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // This method is responsible for sending a weather request message to an Apache Kafka topic.
    public void sendWeatherRequest(String city) {
        kafkaTemplate.send(TOPIC, city);
    }
}