package com.eyedesign.scheduler.domain.time;

import com.eyedesign.scheduler.domain.appointment.Appointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Table(name = "time")
@Entity(name = "time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    boolean isEnabled;
    @NotNull
    String description;
    @NotNull
    @Column(name = "time_data")
    LocalTime timeData;

    @OneToMany(mappedBy = "time", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;

    public boolean getIsEnabled(){
        return this.isEnabled;
    }

    public Time(String description, LocalTime timeData, boolean isEnabled){
        this.description = description;
        this.isEnabled = isEnabled;
        this.timeData = timeData;
    }
}
