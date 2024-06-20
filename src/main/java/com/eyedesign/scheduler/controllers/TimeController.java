package com.eyedesign.scheduler.controllers;

import com.eyedesign.scheduler.domain.time.CreateTimeDTO;
import com.eyedesign.scheduler.domain.time.TimeDetailsDTO;
import com.eyedesign.scheduler.services.TimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("time")
public class TimeController {
    @Autowired
    TimeService timeService;

    @PostMapping("/create")
    public ResponseEntity<TimeDetailsDTO> createTime(@RequestBody @Valid CreateTimeDTO data){
        TimeDetailsDTO createdTme = timeService.createTime(data);
        return ResponseEntity.ok(createdTme);
    }
}
