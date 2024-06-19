package com.eyedesign.scheduler.domain.user;

public record ReturnUserDTO(
        String id,
        String email,
        String firstname,
        String lastname,
        String phone,
        UserRole role
) {
}

