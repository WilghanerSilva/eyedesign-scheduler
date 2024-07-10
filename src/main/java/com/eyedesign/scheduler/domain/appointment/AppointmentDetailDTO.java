package com.eyedesign.scheduler.domain.appointment;

import java.time.LocalDate;

public record AppointmentDetailDTO(String id, String userID, String timeID, LocalDate date, boolean confirmed, boolean reminderSent) {
}
