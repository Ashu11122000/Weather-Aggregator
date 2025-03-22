package com.weather.weather_data_aggregator.controller;

import com.weather.weather_data_aggregator.model.WeatherData;
import com.weather.weather_data_aggregator.service.WeatherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController  // Marks the class as a REST API controller, returning data (JSON/XML) instead of HTML views.
@RequestMapping("/api/weather")  // Defines the base URL for the REST endpoints in the controller.
public class WeatherController {
    /* It declares a dependency on the WeatherService class inside another class, typically a controller.
       The final keyword ensures that the weatherService instance cannot be reassigned after initialization. */
    private final WeatherService weatherService;

    // This is a constructor in the WeatherController class that injects the WeatherService dependency.
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /*  It fetches historical weather data for a specific city within an optional date range. */
    @GetMapping("/history")
    public ResponseEntity<List<WeatherData>> getHistoricalWeather(
            @RequestParam String city,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {

        // It retrieves historical weather data for a given city within a specified date range (startDate to endDate).
        List<WeatherData> history = weatherService.getHistoricalWeatherByCity(city, startDate, endDate);

        // used in a Spring Boot REST API to return an HTTP response with a 200 OK status and a list of weather data (history) as the response body.
        return ResponseEntity.ok(history);
    }

}

// @GetMapping: @GetMapping is an annotation in Spring Boot used to map HTTP GET requests to specific handler methods in a controller.
// RequestParam: @RequestParam is an annotation in Spring Boot used to extract query parameters from an HTTP request.
//@DateTimeFormat: @DateTimeFormat is an annotation in Spring Boot used to specify how a date and time value should be parsed from an HTTP request.