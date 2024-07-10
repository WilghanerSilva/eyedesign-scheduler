package com.eyedesign.scheduler.domain.appointment;

import com.eyedesign.scheduler.domain.time.Time;
import com.eyedesign.scheduler.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name="appointment")
@Entity(name="appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="day_date")
    private LocalDate date;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name="userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="timeID", nullable = false)
    private Time time;

    @Column(name="reminder_sent")
    private boolean reminderSent;

    public Appointment(LocalDate date, boolean confirmed, Time time, User user){
        this.date = date;
        this.confirmed = confirmed;
        this.time = time;
        this.user = user;
    }
}
