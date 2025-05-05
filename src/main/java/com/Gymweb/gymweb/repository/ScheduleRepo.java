package com.Gymweb.gymweb.repository;

import com.Gymweb.gymweb.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    // Custom queries for Schedule
    // For example, to find schedules by class name:
    List<Schedule> findByClasses_Name(String className);
}