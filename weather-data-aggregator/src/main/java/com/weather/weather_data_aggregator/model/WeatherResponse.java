package com.weather.weather_data_aggregator.model;

import java.time.LocalDateTime;

public class WeatherResponse {
    private String city;
    private double temperature;
    private String description;
    private double windSpeed;
    private int humidity;
    private LocalDateTime timestamp;

    public WeatherResponse() {}

    public WeatherResponse(String city, double temperature, String description, double windSpeed, int humidity, LocalDateTime timestamp) {
        super();
        this.city = city;
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", description='" + description + '\'' +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", timestamp=" + timestamp +
                '}';
    }
}
