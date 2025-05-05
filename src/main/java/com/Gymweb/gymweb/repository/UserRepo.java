package com.Gymweb.gymweb.repository;

import com.Gymweb.gymweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // You can add custom queries for User here if needed
    Optional<User> findByEmail(String email);
}
