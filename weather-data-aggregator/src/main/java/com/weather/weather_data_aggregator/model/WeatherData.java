package com.weather.weather_data_aggregator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "weather_data")
public class WeatherData {

    @Id
    private String id;

    private String city;
    private double temperature;
    private String description;
    private double windSpeed;
    private int humidity;
    private LocalDateTime timeStamp;

    public WeatherData() {}

    public WeatherData(String id, String city, double temperature, String description, double windSpeed, int humidity, LocalDateTime timeStamp) {
        super();
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.description = description;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", temperature=" + temperature +
                ", description='" + description + '\'' +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
