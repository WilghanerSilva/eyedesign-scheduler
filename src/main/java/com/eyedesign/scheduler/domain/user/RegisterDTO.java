package com.eyedesign.scheduler.domain.user;

public record RegisterDTO(
        String email,
        String password,
        String firstname,
        String lastname,
        String phone
) {
}
