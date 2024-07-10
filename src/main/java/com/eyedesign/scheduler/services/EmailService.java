package com.eyedesign.scheduler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendRemenderMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Seu agendamento está próximo");
        message.setText("Olá querido usuário, viemos por meio deste email " +
                "informá-lo que seu agendamento é hoje daqui 1 hora.");

        mailSender.send(message);
        System.out.println("Enviando Email...");
    }
}
