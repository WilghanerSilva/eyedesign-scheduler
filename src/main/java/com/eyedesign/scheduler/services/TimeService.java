package com.eyedesign.scheduler.services;

import com.eyedesign.scheduler.domain.time.CreateTimeDTO;
import com.eyedesign.scheduler.domain.time.Time;
import com.eyedesign.scheduler.domain.time.TimeDetailsDTO;
import com.eyedesign.scheduler.infra.errors.ConflictException;
import com.eyedesign.scheduler.infra.errors.InvalidDataException;
import com.eyedesign.scheduler.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

@Autowired
    TimeRepository repository;

    public TimeDetailsDTO createTime (CreateTimeDTO data) throws ConflictException {
        if(this.repository.findByDescription(data.description()).isPresent())
            throw new ConflictException("O horário já existe");

        Time newTime = new Time(data.description(), data.timeData(), true);
        var createdTime = this.repository.save(newTime);


        return new TimeDetailsDTO(createdTime.getDescription(), createdTime.getId(), createdTime.getIsEnabled(), createdTime.getTimeData());
    }

    public List<TimeDetailsDTO> listEnabledTimes() {
        List<Time> times = this.repository.findAll();

        return times.stream()
                .filter(Time::getIsEnabled)
                .map(time -> {return new TimeDetailsDTO(time.getDescription(), time.getId(), time.getIsEnabled(), time.getTimeData());})
                .toList();
    }

    public void disableTime(String id) throws InvalidDataException{
        Optional<Time> optionalTime = this.repository.findById(id);

        if(optionalTime.isEmpty())
            throw new InvalidDataException("id inválido");

        Time time = optionalTime.get();
        time.setEnabled(false);

        this.repository.save(time);
    }

    public void enableTime(String id) throws InvalidDataException{
        Optional<Time> optionalTime = this.repository.findById(id);

        if(optionalTime.isEmpty())
            throw new InvalidDataException("O id fornecido é inválido");

        Time time = optionalTime.get();
        time.setEnabled(true);

        this.repository.save(time);
    }

    public List<TimeDetailsDTO> listAllTimes() {
        List<Time> times = this.repository.findAll();

        return times.stream()
                .map(time -> {
                    return new TimeDetailsDTO(time.getDescription(), time.getId(), time.getIsEnabled(), time.getTimeData());
                }).toList();
    }

    public void delete(String id) throws Exception{
        Optional<Time> optionalTime = this.repository.findById(id);

        if(optionalTime.isEmpty())
            throw new Exception("id inválido");

        Time time = optionalTime.get();

        this.repository.delete(time);
    }
}
