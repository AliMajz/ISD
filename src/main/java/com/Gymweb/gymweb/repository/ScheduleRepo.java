package com.Gymweb.gymweb.repository;

import com.Gymweb.gymweb.entity.Days;
import com.Gymweb.gymweb.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    List<Schedule> findByStartTimeBetweenAndCoachIdAndDaysIn(LocalTime startTime, LocalTime endTime, Long coachId, List<Days> days);
}