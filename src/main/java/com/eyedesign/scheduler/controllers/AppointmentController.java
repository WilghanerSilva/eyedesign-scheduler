package com.eyedesign.scheduler.controllers;

import com.eyedesign.scheduler.domain.appointment.CreateAppointmentControllerDTO;
import com.eyedesign.scheduler.domain.appointment.AppointmentDetailDTO;
import com.eyedesign.scheduler.domain.appointment.CreateAppointmentDTO;
import com.eyedesign.scheduler.services.AppointmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    AppointmentService service;

    @PostMapping("/make")
    public ResponseEntity<AppointmentDetailDTO> makeAppointment(@RequestBody @Valid CreateAppointmentControllerDTO data, HttpServletRequest request) throws Exception{
        String userEmail = (String) request.getAttribute("email");
        LocalDate parseDate = LocalDate.parse(data.date());

        CreateAppointmentDTO createData = new CreateAppointmentDTO(parseDate,false, userEmail, data.timeID());

        return ResponseEntity.ok(this.service.createAppointment(createData));
    }
}
