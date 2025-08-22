package band.effective.coffieshop.model;

import band.effective.coffieshop.model.weatherResponse.WeatherResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    private final String API_KEY = "fe8104433cf151760097f65355a060b3";
    private final String CITY = "Murmansk";
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
