package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.dto.NotificationDTO;

public interface IEmailService {
    void sendMessage(String toAddress, String subject, String message);

    void sendToGroup(NotificationDTO notificationDTO);
    void sendWeather();

    void sendHappyBirthday();

    void sendPoints();

    void sendWeaklyEmpty();
}
