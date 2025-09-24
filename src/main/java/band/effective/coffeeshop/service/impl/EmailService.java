package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.dto.NotificationDTO;
import band.effective.coffeeshop.repository.CustomerRepository;
import band.effective.coffeeshop.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
@AllArgsConstructor
@Primary
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender sender;
    private CustomerRepository customerRepository;
    private WeatherService weatherService;

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

    @Override
    public void sendMessage(String toAddress, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        sender.send(simpleMailMessage);
    }
    @Override
    public void sendToGroup(NotificationDTO notificationDTO){
        notificationDTO.getId()
                .forEach(id-> sendMessage(customerRepository.findById(id).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"incorrect id - "+id)
                        ).getEmail(),
                        notificationDTO.getSubject(), notificationDTO.getMessage()));
    }
    @Override
    public void sendWeather(){
        var weather = weatherService.getWeather();
        if (weather.getMain().getTempMax()>EmailService.MONTH_STATS.get(LocalDate.now().getMonthValue()).get("max")){
            customerRepository.findAll()
                    .forEach(customer-> sendMessage(
                            customer.getEmail(),
                            "Приходите за холодным кофе в жаркую погоду",
                            String.format("За окном %d, а значит пора освежиться холодным кофе в effective coffee shop",weather.getMain().getFeelsLike().intValue())
                    ));
        }
        else if (weather.getMain().getTempMax()<EmailService.MONTH_STATS.get(LocalDate.now().getMonthValue()).get("min")){
            customerRepository.findAll()
                    .forEach(customer-> sendMessage(
                            customer.getEmail(),
                            "Согревайтесь горячим кофе в холодную погоду",
                            String.format("На улице %d, а значит нужно согреться горячим кофе в effective coffee shop",weather.getMain().getFeelsLike().intValue())
                    ));
        }
    }
    @Override
    public void sendHappyBirthday() {
        customerRepository.findAll().stream().filter(customer ->
                customer.getBirthday().getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
                        customer.getBirthday().getMonth().equals(LocalDate.now().getMonth())).forEach(
                customer -> sendMessage(customer.getEmail(), "С днем рождения!\uD83C\uDF82",
                        String.format("%s, Effective coffee shop поздравляет вас с днем рождения!\uD83C\uDF89",
                                customer.getName()))
        );
    }
    @Override
    public void sendPoints() {
        customerRepository.findAll().stream()
                .filter(customer -> customer.getLastOrder() != null &&
                        customer.getLastOrder().isAfter(LocalDate.now().minusDays(7)))
                .forEach(customer -> {
                    sendMessage(
                            customer.getEmail(),
                            "Баллы за неделю\uD83E\uDE99"
                            , String.format(
                                    "%s, за неделю вы накопили %f баллов\uD83E\uDD11\nЭто повод зайти и выпить кофе в effective coffee shop☕",
                                    customer.getName(), customer.getWeaklyPoints()
                            )
                    );
                    customer.setWeaklyPoints(BigDecimal.valueOf(0));
                });
    }
    @Override
    public void sendWeaklyEmpty(){
        customerRepository.findAll().stream()
                .filter(customer ->
                        customer.getLastOrder().isBefore(LocalDate.now().minusDays(7)))
                .forEach(customer -> sendMessage(
                        customer.getEmail(),
                        "Effective coffee shop скучает без вас"
                        ,String.format(
                                "%s, вас не было уже неделю в нашей кофейне\uD83D\uDE22\nЭто повод зайти и выпить кофе в effective coffee shop☕",
                                customer.getName()
                        )
                ));
    }
}
