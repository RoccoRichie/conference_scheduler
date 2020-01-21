package com.roccorichie.scheduler.repositories;

import com.roccorichie.scheduler.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    // We will have CRUD operations now on our DB
}
