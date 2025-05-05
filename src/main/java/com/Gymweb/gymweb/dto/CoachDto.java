package com.Gymweb.gymweb.dto;

import com.Gymweb.gymweb.entity.Classes;
import com.Gymweb.gymweb.entity.Coach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CoachDto {
    private Long id;
    private String fullName;
    private String specialty;
    private String createdBy;
    private String updatedBy;

    public static CoachDto toDto(Coach coach) {
        return CoachDto.builder()
                .id(coach.getId())
                .fullName(coach.getFullName())
                .specialty(coach.getSpecialty())
                .createdBy(coach.getCreatedBy())
                .updatedBy(coach.getUpdatedBy())
                .build();
    }
}

