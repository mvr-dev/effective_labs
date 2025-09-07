package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.City;
import band.effective.coffeeshop.model.weatherResponse.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Value("${WEATHER_API_KEY}")
    private final String API_KEY;
    private final City CITY = City.Omsk;

    private final RestClient restClient = RestClient.create();


    @Cacheable(value = "weather", key = "#city")
    public WeatherResponse getWeather(){
        String URL = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", CITY)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .toUriString();
        var response = restClient
                .get()
                .uri(URL)
                .retrieve()
                .toEntity(WeatherResponse.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
}