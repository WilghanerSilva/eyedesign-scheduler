package com.eyedesign.scheduler.domain.time;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.*;

@Table(name = "time")
@Entity(name = "time")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    boolean isEnabled;
    @NotNull
    String description;

    public boolean getIsEnabled(){
        return this.isEnabled;
    }

    public Time(String description, boolean isEnabled){
        this.description = description;
        this.isEnabled = isEnabled;
    }
}
