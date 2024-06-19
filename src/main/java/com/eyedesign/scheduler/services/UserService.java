package com.eyedesign.scheduler.services;

import com.eyedesign.scheduler.domain.user.RegisterDTO;
import com.eyedesign.scheduler.domain.user.ReturnUserDTO;
import com.eyedesign.scheduler.domain.user.User;
import com.eyedesign.scheduler.domain.user.UserRole;
import com.eyedesign.scheduler.infra.errors.ConflictException;
import com.eyedesign.scheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public ReturnUserDTO createUser(RegisterDTO data) throws ConflictException {
        if(this.repository.findByEmail(data.email()) != null)
            throw new ConflictException("Email em uso");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.firstname(), data.lastname(), data.phone(), UserRole.USER);

        var createdUser = this.repository.save(newUser);

        return new ReturnUserDTO(
                createdUser.getId(),
                createdUser.getEmail(),
                createdUser.getFirstname(),
                createdUser.getLastname(),
                createdUser.getPhone(),
                createdUser.getRole()
        );
    }
}
