package com.eyedesign.scheduler.domain.time;

import java.time.LocalTime;

public record CreateTimeDTO(String description, LocalTime timeData) {
}
