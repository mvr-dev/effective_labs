package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.dto.NotificationDTO;
import band.effective.coffeeshop.service.ICustomerService;
import band.effective.coffeeshop.service.IEmailService;
import band.effective.coffeeshop.service.impl.CustomerService;
import band.effective.coffeeshop.service.impl.EmailService;
import band.effective.coffeeshop.service.impl.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {
    private WeatherService weatherService;
    private IEmailService emailService;

    private final ICustomerService customerService;

    @GetMapping("/toGroup")
    public void sendToGroup(@RequestBody NotificationDTO notificationDTO){
        emailService.sendToGroup(notificationDTO);
    }
    @Scheduled(cron = "0 0 9 * * *")
    public void sendWeather(){
        emailService.sendWeather();
    }
    @Scheduled(cron = "0 0 12 * * *")
    public void sendHappyBirthday() {
        emailService.sendHappyBirthday();
    }

    @Scheduled(cron = "@weekly")
    public void sendPoints(){
        emailService.sendPoints();
    }
    @Scheduled(cron = "@weekly")
    public void sendWeaklyEmpty(){
        emailService.sendWeaklyEmpty();
    }
}
