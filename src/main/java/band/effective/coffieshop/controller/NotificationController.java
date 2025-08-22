package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.Customer;
import band.effective.coffieshop.model.NotificationService;
import band.effective.coffieshop.model.dto.NotificationDTO;
import band.effective.coffieshop.model.weatherResponse.WeatherResponse;
import band.effective.coffieshop.service.IEmailService;
import band.effective.coffieshop.service.impl.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {
    private NotificationService service;
    private IEmailService emailService;

    private final CustomerService customerService;
    public static final Map<Integer, Map<String, Integer>> MONTH_STATS = Map.ofEntries(
            Map.entry(1,  Map.of("max", 20, "min", -20)), // Январь
            Map.entry(2,  Map.of("max", 20,  "min", -20)), // Февраль
            Map.entry(3,  Map.of("max", 20,   "min", -5)), // Март
            Map.entry(4,  Map.of("max", 20,  "min", 0)),   // Апрель
            Map.entry(5,  Map.of("max", 22,  "min", 10)),  // Май
            Map.entry(6,  Map.of("max", 22,  "min", 15)),  // Июнь
            Map.entry(7,  Map.of("max", 22,  "min", 18)),  // Июль
            Map.entry(8,  Map.of("max", 20,  "min", 16)),  // Август
            Map.entry(9,  Map.of("max", 20,  "min", 10)),  // Сентябрь
            Map.entry(10, Map.of("max", 15,  "min", 5)),   // Октябрь
            Map.entry(11, Map.of("max", 20,   "min", -5)),  // Ноябрь
            Map.entry(12, Map.of("max", 20,  "min", -20))  // Декабрь
    );


    @GetMapping
    public List<WeatherResponse> responses(){
        return List.of(service.getWeather());
    }
    @GetMapping("/hello/{id}")
    public void sendHelloTo(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        emailService.sendMessage(customer.getEmail(),"Hello from effective coffeeshop",
                String.format("Hello, dear %s!\nWe are glad to see you at our coffeeshop!",customer.getName()));
    }
    @GetMapping("/toGroup")
    public void sendToGroup(@RequestBody NotificationDTO notificationDTO){
        notificationDTO.getId()
                .forEach(id->emailService.sendMessage(customerService.getCustomerById(id).getEmail(),
                        notificationDTO.getSubject(), notificationDTO.getMessage()));
    }
    @Scheduled(cron = "0 0 12 * * *")
    public void sendWeather(){
        System.out.println("send");
        var weather = service.getWeather();
        if (weather.getMain().getTempMax()>MONTH_STATS.get(LocalDate.now().getMonthValue()).get("max")){
            customerService.getAllCustomers()
                    .forEach(customer-> emailService.sendMessage(
                            customer.getEmail(),
                            "Приходите за холодным кофе в жаркую погоду",
                            String.format("За окном %d, а значит пора освежиться холодным кофе в effective coffee shop",weather.getMain().getFeelsLike().intValue())
                    ));
        }
        else if (weather.getMain().getTempMax()<MONTH_STATS.get(LocalDate.now().getMonthValue()).get("min")){
            customerService.getAllCustomers()
                    .forEach(customer-> emailService.sendMessage(
                            customer.getEmail(),
                            "Согревайтесь горячим кофе в холодную погоду",
                            String.format("На улице %d, а значит нужно согреться горячим кофе в effective coffee shop",weather.getMain().getFeelsLike().intValue())
                    ));
        }
    }
    @Scheduled(cron = "0 0 12 * * *")
    public void sendHappyBirthday() {
        customerService.getAllCustomers().stream().filter(customer ->
                customer.getBirthday().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
                        customer.getBirthday().getMonth().equals(LocalDate.now().getMonth())).forEach(
                                customer -> emailService.sendMessage(customer.getEmail(), "С днем рождения!\uD83C\uDF82",
                                        String.format("%s, Effective coffee shop поздравляет вас с днем рождения!\uD83C\uDF89",
                                        customer.getName()))
        );

    }
}
