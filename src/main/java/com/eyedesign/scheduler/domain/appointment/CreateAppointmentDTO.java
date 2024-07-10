package com.eyedesign.scheduler.domain.appointment;

import java.time.LocalDate;

public record CreateAppointmentDTO(LocalDate date, boolean confirmed, boolean reminderSent, String userEmail, String timeID) {
}
