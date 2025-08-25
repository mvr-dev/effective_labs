package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Primary
public class EmailService implements IEmailService {
    @Autowired
    public JavaMailSender sender;

    public void sendMessage(String toAddress, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        sender.send(simpleMailMessage);
    }

}
