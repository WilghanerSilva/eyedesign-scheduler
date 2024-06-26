package com.eyedesign.scheduler.repositories;

import com.eyedesign.scheduler.domain.appointment.Appointment;
import com.eyedesign.scheduler.domain.time.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Query(value = "SELECT * from appointment " +
            "WHERE appointment.day_date = :date and " +
            "appointment.timeID = :timeID",
            nativeQuery = true
    )
    Appointment findByDateAndTime(@Param("timeID") String timeID, @Param("date") LocalDate date);

}
