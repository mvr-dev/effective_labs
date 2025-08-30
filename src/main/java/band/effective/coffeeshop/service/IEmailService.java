package band.effective.coffeeshop.service;

public interface IEmailService {
    void sendMessage(String toAddress, String subject, String message);
}
