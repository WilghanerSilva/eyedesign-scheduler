package com.eyedesign.scheduler.services;
import com.eyedesign.scheduler.domain.appointment.Appointment;
import com.eyedesign.scheduler.domain.time.Time;
import com.eyedesign.scheduler.domain.user.User;
import com.eyedesign.scheduler.repositories.AppointmentRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@NoArgsConstructor
public class WhatsappService {
    @Value("${api.twilio.account_sid}")
    private String ACCOUNT_SID;

    @Value("${api.twilio.auth_token}")
    private String AUTH_TOKEN;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void SendReminderMessage(Appointment appointment) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        User user = appointment.getUser();
        Time time = appointment.getTime();

        PhoneNumber receiverPhoneNumber = new PhoneNumber("whatsapp:"+user.getPhone());
        PhoneNumber senderPhoneNumber = new PhoneNumber("whatsapp:+14155238886");

        String body = MessageFormat.format("Olá {0} seu agendamento marcado para {1} está próximo",
                user.getFirstname(),
                time.getTimeData().toString());

        Message message = Message.creator(receiverPhoneNumber, senderPhoneNumber, body).create();

        appointment.setReminderSent(true);
        appointmentRepository.save(appointment);
    }
}
