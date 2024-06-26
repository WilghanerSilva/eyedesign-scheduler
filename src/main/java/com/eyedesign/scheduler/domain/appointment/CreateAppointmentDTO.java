package com.eyedesign.scheduler.domain.appointment;

import java.time.LocalDate;

public record CreateAppointmentDTO(LocalDate date, boolean confirmed, String userEmail, String timeID) {
}
