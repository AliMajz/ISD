package com.Gymweb.gymweb.service;

import com.Gymweb.gymweb.dto.ScheduleDto;
import com.Gymweb.gymweb.entity.Member;
import com.Gymweb.gymweb.entity.Schedule;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.CoachRepo;
import com.Gymweb.gymweb.repository.MemberRepo;
import com.Gymweb.gymweb.repository.ScheduleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService extends BaseService<Schedule>{

    @Autowired
    private ScheduleRepo repository;
    private MemberRepo memberRepo;

    @Autowired
    private CoachRepo coachRepo;

    @Override
    protected void validateUnique(Schedule entity) throws ValidationException {
        // Implement validation logic for unique schedule entries if needed
        // For example, check if a schedule with the same date and time already exists
        var res = repository.findByStartTimeBetweenAndCoachIdAndDaysIn(entity.getStartTime(), entity.getEndTime(), entity.getCoach().getId(), entity.getDays());

        if(res.size() >0 ){
            throw new ValidationException("Schedule already exists for this time slot.");
        }
    }

    public Schedule add(ScheduleDto dto) throws ValidationException {

        //check if coach exists
        var coach = coachRepo.findById(dto.getCoachId()).orElseThrow(() -> new ValidationException("Coach not found"));

        //convert dto to entity

        Schedule entity = Schedule.builder()
                .coach(coach)
                .workoutType(dto.getWorkoutType())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .days(dto.getDays())
                .build();

        // Implement any additional logic before adding a schedule if needed
        validateUnique(entity);
        return super.add(entity);
    }
}
