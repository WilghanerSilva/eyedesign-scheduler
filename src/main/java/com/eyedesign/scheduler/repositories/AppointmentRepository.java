package com.eyedesign.scheduler.repositories;

import com.eyedesign.scheduler.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Query(value = "SELECT * from appointment " +
            "WHERE appointment.day_date = :date and " +
            "appointment.timeID = :timeID",
            nativeQuery = true
    )
    Appointment findByDateAndTime(@Param("timeID") String timeID, @Param("date") LocalDate date);

    @Query(value = "SELECT * from appointment " +
            "WHERE appointment.userID = :userID and " +
            "appointment.confirmed = 1", nativeQuery = true)
    List<Appointment> findByUser(@Param("userID")String userID);

    @Query(value = "SELECT appointment.* FROM appointment " +
            "JOIN time ON time.id = appointment.timeID " +
            "WHERE time_data BETWEEN CURTIME() AND ADDTIME(CURTIME(), '01:00:00') " +
            "AND day_date = CURDATE() " +
            "AND reminder_sent = 0 " +
            "AND confirmed = 1", nativeQuery = true)
    List<Appointment> findAppointmentsWithNextHour();
}
