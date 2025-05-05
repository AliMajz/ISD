package com.Gymweb.gymweb.repository;

import com.Gymweb.gymweb.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
    // Custom queries for Rating
    // For example, to find ratings by member or coach:
    List<Rating> findByMemberId(Long memberId);
    List<Rating> findByCoachId(Long coachId);
}
