package com.eyedesign.scheduler.controllers;

import com.eyedesign.scheduler.domain.time.CreateTimeDTO;
import com.eyedesign.scheduler.domain.time.TimeDetailsDTO;
import com.eyedesign.scheduler.services.TimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list-enabled")
    public ResponseEntity<List<TimeDetailsDTO>> listEnabledTimes() {
        List<TimeDetailsDTO> times = this.timeService.listEnabledTimes();

        return ResponseEntity.ok(times);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<TimeDetailsDTO>> listAll() {
        List<TimeDetailsDTO> times = this.timeService.listAllTimes();

        return ResponseEntity.ok(times);
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<Void> disableTime(@PathVariable String id) throws Exception{
        this.timeService.disableTime(id);

        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable String id) throws Exception{
        this.timeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
