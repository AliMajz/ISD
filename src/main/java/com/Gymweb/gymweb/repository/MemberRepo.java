package com.Gymweb.gymweb.repository;

import com.Gymweb.gymweb.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    // Custom queries for Member can go here
    // e.g. find by email:
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);

    Optional<Member> findByEmailIgnoreCase(String email);

    @Query("SELECT m FROM Member m WHERE m.endDate < CUREENT_DATE")
    List<Member> getAllExpiredMembers();

}
