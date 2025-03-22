package com.weather.weather_data_aggregator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weather_data_aggregator.model.WeatherData;
import com.weather.weather_data_aggregator.repository.WeatherRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import java.time.LocalDateTime;
import java.util.List;

/* The @Service annotation in Spring is a specialization of @Component that is used to indicate that
   a class contains business logic and should be treated as a service layer component in a Spring
   application.*/
@Service
public class WeatherService {

    // Annotation in Spring to manage dependencies automatically.
    @Autowired
    private final WeatherRepository weatherRepository;  //  ensures dependency injection is properly handled in Spring Boot.
    private final RestTemplate restTemplate;  // declare a dependency on RestTemplate, which is a Spring class for making HTTP requests to external APIs.

    /* constant declaration in Java, typically found inside a class that interacts with the OpenWeatherMap API to fetch weather data. */
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY = "05600e7965b50cde0f956670c0bd6dbe";  // declares a constant API key for accessing an external API (in this case, OpenWeatherMap).

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    // part of a Spring service class and is responsible for initializing dependencies (weatherRepository and restTemplate).
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = new RestTemplate();
    }

    // This method fetches weather data from an external API (such as OpenWeatherMap) for a given city and returns a WeatherData object.
    public WeatherData fetchWeatherFromAPI(String city) {
        try {

            // This line sends an HTTP GET request to an API (e.g., OpenWeatherMap) using RestTemplate and retrieves the response as a ResponseEntity<String>.
            ResponseEntity<String> response = restTemplate.getForEntity(API_URL + city + "&units=metric&appid=" + API_KEY, String.class);

            // Print API response for debugging
            /* When you make an HTTP request using RestTemplate.getForEntity(), the method returns a ResponseEntity object, which contains:
               1. HTTP status code (e.g., 200 OK, 404 Not Found)
               2. HTTP headers
               3. Response body (the actual data sent from the API)
               4. response.getBody() is used to retrieve the content of an HTTP response when making REST API calls using RestTemplate. */
            System.out.println("API Response: " + response.getBody());

            /* ObjectMapper is a class from the Jackson library (com.fasterxml.jackson.databind.ObjectMapper)
               used to convert JSON data into Java objects. */
            ObjectMapper objectMapper = new ObjectMapper();

            // JsonNode represents the JSON as a tree structure, allowing you to navigate and extract data without creating a Java class.
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // This line extracts the "name" field from a JSON response and converts it into a Java String.
            String cityName = jsonNode.get("name").asText();

            // This line extracts the temperature value from a JSON response and converts it into a double.
            double temperature = jsonNode.get("main").get("temp").asDouble();

            //This line extracts the humidity value from a JSON response and converts it into an int.
            int humidity = jsonNode.get("main").get("humidity").asInt();

            // This line extracts the weather description (e.g., "clear sky", "rainy", "cloudy") from a JSON response and converts it into a String.
            String weatherDescription = jsonNode.get("weather").get(0).get("description").asText();

            //This line creates a timestamp representing the current date and time using Java's LocalDateTime class.
            LocalDateTime timestamp = LocalDateTime.now();

            // This line creates an instance of the WeatherData class by passing values (cityName, temperature, humidity, weatherDescription, timestamp) to its constructor.
            WeatherData weatherData = new WeatherData(cityName, temperature, humidity, weatherDescription, timestamp);

            return weatherData;

        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error fetching weather data", e);
        }
    }

    // This method saves a WeatherData object to a database using weatherRepository.
    public void saveWeatherData(WeatherData weatherData) {
        weatherRepository.save(weatherData);
    }

    // This method is designed to retrieve historical weather data for a specific city within a given date range.
    public List<WeatherData> getHistoricalWeatherByCity(String city, LocalDateTime startDate, LocalDateTime endDate) {
        // line of code retrieves a list of weather records from the database for a specific city.
        List<WeatherData> data = weatherRepository.findByCity(city);
        if (data.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No weather data found for city: " + city);
        }
        return data;
    }


    public void fetchWeatherData() {
        logger.info("Fetching weather data...");
        // Fetch and process data
        logger.debug("Weather data processed successfully");
    }
}