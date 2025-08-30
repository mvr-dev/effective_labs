package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.weatherResponse.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Value("${spring.weather.api-key}")
    private String API_KEY;
    private final String CITY = "Omsk";
    private final String URL = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=ru&cnt=8",CITY,API_KEY);

    private final RestClient restClient = RestClient.create();


    //    @Scheduled(fixedRate = 30_000)
    @Cacheable
    public WeatherResponse getWeather(){
        //        System.out.println(API_KEY);
        var response = restClient
                .get()
                .uri(URL)
                .retrieve()
                .toEntity(WeatherResponse.class);
        //        System.out.println(response);
        return response.getBody();
    }
}