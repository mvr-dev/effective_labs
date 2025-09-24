package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.City;
import band.effective.coffeeshop.model.weatherResponse.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Value("${WEATHER_API_KEY}")
    private String API_KEY;

    private RestClient restClient = RestClient.create();
    @Retryable(maxAttempts = 4,backoff = @Backoff(delay = 1000))
    @Cacheable(value = "weather", key = "#city")
    public WeatherResponse getWeather(City city){
        String URL = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .toUriString();
        var response = restClient
                .get()
                .uri(URL)
                .retrieve()
                .toEntity(WeatherResponse.class);
        return response.getBody();
    }
}