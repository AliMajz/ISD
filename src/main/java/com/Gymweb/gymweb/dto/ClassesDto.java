package com.Gymweb.gymweb.dto;

import com.Gymweb.gymweb.entity.Classes;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClassesDto {
    private Long id;
    private String className;
    private LocalTime startTime;
    private LocalTime endTime;

    public static ClassesDto toDto(Classes classes){
        return ClassesDto.builder()
                .id(classes.getId())
                .className(classes.getName())
                .startTime(classes.getStartTime())
                .endTime(classes.getEndTime())
                .build();
    }
}
