package com.roccorichie.scheduler.repositories;

import com.roccorichie.scheduler.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
