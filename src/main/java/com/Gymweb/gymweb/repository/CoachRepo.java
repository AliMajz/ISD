package com.Gymweb.gymweb.repository;

import com.Gymweb.gymweb.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepo extends JpaRepository<Coach, Long> {
    // You can add custom queries, for example:
    Optional<Coach> findByEmail(String email);
}
