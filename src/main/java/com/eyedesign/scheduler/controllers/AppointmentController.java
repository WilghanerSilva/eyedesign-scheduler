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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("/confirm/{id}")
    public ResponseEntity<AppointmentDetailDTO> confirmAppointment(@PathVariable String id) throws Exception{
        return ResponseEntity.ok(
                this.service.confirmAppointment(id)
        );
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<AppointmentDetailDTO>> listAllAppointments(){
        return ResponseEntity.ok(this.service.listAll());
     }

     @GetMapping("/list-by-user")
    public ResponseEntity<List<AppointmentDetailDTO>> listByUser(HttpServletRequest request) throws Exception{
        String email =(String) request.getAttribute("email");

        return ResponseEntity.ok(this.service.ListByUser(email));
     }
}
