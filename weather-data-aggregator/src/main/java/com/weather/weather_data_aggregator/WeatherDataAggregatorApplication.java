package com.weather.weather_data_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // The @EnableScheduling annotation in Spring Boot is used to enable support for scheduled tasks.
public class WeatherDataAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataAggregatorApplication.class, args);
	}

}
