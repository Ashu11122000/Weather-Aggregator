package com.weather.weather_data_aggregator.model;

public class WeatherRequest {
    private String city;

    public WeatherRequest() {}

    public WeatherRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "WeatherRequest{" +
                "city='" + city + '\'' +
                '}';
    }
}
