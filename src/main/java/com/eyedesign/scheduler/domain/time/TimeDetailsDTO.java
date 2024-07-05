package com.eyedesign.scheduler.domain.time;

import java.time.LocalTime;

public record TimeDetailsDTO(String description, String id, Boolean isEnabled, LocalTime timeData) {
}
