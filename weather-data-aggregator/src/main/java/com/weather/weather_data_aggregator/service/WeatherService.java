package com.weather.weather_data_aggregator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    // This line in your WeatherService class declares a dependency on the WeatherProducer class.
    private final WeatherProducer weatherProducer;

    //This line in your WeatherService class declares a dependency on RestTemplate, which is used for making HTTP requests.
    private final RestTemplate restTemplate;

    //This line in your WeatherService class is used to store the OpenWeather API key for making requests to the OpenWeather API.
    @Value("${openweather.api.key}") // Inject API key from properties
    private String apiKey;

    // This line defines a constant for the base URL of the OpenWeather API.
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    /* This is a constructor in the WeatherService class.
       It is used to initialize dependencies when an object of WeatherService is created. */
    public WeatherService(WeatherProducer weatherProducer, RestTemplate restTemplate) {
        this.weatherProducer = weatherProducer;
        this.restTemplate = restTemplate;
    }

    /* This method is a part of your WeatherService class and is responsible for
       sending a weather request message to a Kafka topic. */
    public void requestWeather(String city) {
        weatherProducer.sendWeatherRequest(city);
    }

    /* This method is responsible for fetching weather data from the OpenWeather API for a given city.
       It constructs a URL dynamically and then makes an API call. */
    public String getWeatherFromOpenWeather(String city) {

        /* %s → Placeholder for BASE_URL, city, and apiKey.
           String.format() → Replaces placeholders with actual values.
            The "&" in a URL is used to separate multiple query parameters in an HTTP request.
            "?q" is a query parameter used in HTTP GET requests to specify the city name when making API calls.
            "appid" is a query parameter used to authenticate your request when calling the OpenWeather API. It stands for API Key (Application ID). */
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, apiKey);


        // This line of code is used to make an HTTP GET request to the given url using RestTemplate in Spring Boot and receive the response.
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Returns JSON response from OpenWeather API
        return response.getBody();
    }
}