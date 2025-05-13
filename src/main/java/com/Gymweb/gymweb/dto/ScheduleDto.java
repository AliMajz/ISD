package com.Gymweb.gymweb.dto;

import com.Gymweb.gymweb.entity.Coach;
import com.Gymweb.gymweb.entity.Days;
import com.Gymweb.gymweb.entity.WorkoutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private Long id;
    private Long coachId;
    private WorkoutType workoutType;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Days> days;
}
