package com.eyedesign.scheduler.repositories;

import com.eyedesign.scheduler.domain.time.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeRepository extends JpaRepository<Time, String> {
    Optional<Time> findByDescription(String description);
}
