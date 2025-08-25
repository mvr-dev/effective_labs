package band.effective.coffieshop.service;

public interface IEmailService {
    void sendMessage(String toAddress, String subject, String message);
}
