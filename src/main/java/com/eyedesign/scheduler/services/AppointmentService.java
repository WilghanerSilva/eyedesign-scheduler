package com.eyedesign.scheduler.services;

import com.eyedesign.scheduler.domain.appointment.Appointment;
import com.eyedesign.scheduler.domain.appointment.AppointmentDetailDTO;
import com.eyedesign.scheduler.domain.appointment.CreateAppointmentDTO;
import com.eyedesign.scheduler.domain.time.Time;
import com.eyedesign.scheduler.domain.user.User;
import com.eyedesign.scheduler.infra.errors.ConflictException;
import com.eyedesign.scheduler.infra.errors.InvalidDataException;
import com.eyedesign.scheduler.repositories.AppointmentRepository;
import com.eyedesign.scheduler.repositories.TimeRepository;
import com.eyedesign.scheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private UserRepository userRepository;

    public AppointmentDetailDTO createAppointment(CreateAppointmentDTO data) throws ConflictException, InvalidDataException {
        Appointment appointment = this.appointmentRepository.findByDateAndTime(data.timeID(), data.date());

        if(data.date().isBefore(LocalDate.now()))
            throw new InvalidDataException("Data inválida");

        if(appointment != null)
            throw new ConflictException("O horário já foi reservado");

        User user =(User) this.userRepository.findByEmail(data.userEmail());
        Optional<Time> optionalTime = this.timeRepository.findById(data.timeID());

        if(user == null)
            throw new InvalidDataException("Usuário Inválido");

        if(optionalTime.isEmpty())
            throw new InvalidDataException("Horário inválido");

        Time time = optionalTime.get();

        Appointment newAppointment = new Appointment(data.date(), false, time, user);

        appointmentRepository.save(newAppointment);

        return new AppointmentDetailDTO(
                newAppointment.getId(),
                user.getId(),
                time.getId(),
                newAppointment.getDate(),
                newAppointment.isConfirmed()
        );
    }

    public AppointmentDetailDTO confirmAppointment(String id) {
        Optional<Appointment> optionalAppointment = this.appointmentRepository.findById(id);

        if(optionalAppointment.isEmpty())
            throw new InvalidDataException("Agendamento inválido");

        Appointment appointment = optionalAppointment.get();

        appointment.setConfirmed(true);

        this.appointmentRepository.save(appointment);

        return new AppointmentDetailDTO(
                appointment.getId(),
                appointment.getUser().getId(),
                appointment.getTime().getId(),
                appointment.getDate(),
                appointment.isConfirmed()
        );
    }

    public List<AppointmentDetailDTO> listAll() {
        List<Appointment> appointments = this.appointmentRepository.findAll();

        return appointments.stream().map(appointment -> new AppointmentDetailDTO(
                appointment.getId(),
                appointment.getUser().getId(),
                appointment.getTime().getId(),
                appointment.getDate(),
                appointment.isConfirmed()
        )).toList();
    }
}
