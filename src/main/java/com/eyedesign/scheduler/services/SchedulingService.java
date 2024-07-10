package com.eyedesign.scheduler.services;

import com.eyedesign.scheduler.domain.appointment.Appointment;
import com.eyedesign.scheduler.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private WhatsappService whatsappService;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void checkAppointments() {
        List<Appointment> upcomingAppointments = appointmentRepository.findAppointmentsWithNextHour();
        upcomingAppointments.stream().forEach(appointment -> whatsappService.SendReminderMessage(appointment));
    }
}
