package com.eyedesign.scheduler.services;

import com.eyedesign.scheduler.domain.time.CreateTimeDTO;
import com.eyedesign.scheduler.domain.time.Time;
import com.eyedesign.scheduler.domain.time.TimeDetailsDTO;
import com.eyedesign.scheduler.infra.errors.ConflictException;
import com.eyedesign.scheduler.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TimeService {

@Autowired
    TimeRepository repository;

    public TimeDetailsDTO createTime (CreateTimeDTO data) throws ConflictException {
        if(this.repository.findByDescription(data.description()).isPresent())
            throw new ConflictException("O horário já existe");

        Time newTime = new Time(data.description(), true);
        var createdTime = this.repository.save(newTime);

        return new TimeDetailsDTO(createdTime.getDescription(), createdTime.getId(), createdTime.getIsEnabled());
    }

    public List<TimeDetailsDTO> listEnabledTimes() {
        List<Time> times = this.repository.findAll();

        return times.stream()
                .filter(Time::getIsEnabled)
                .map(time -> {return new TimeDetailsDTO(time.getDescription(), time.getId(), time.getIsEnabled());})
                .toList();
    }
}
