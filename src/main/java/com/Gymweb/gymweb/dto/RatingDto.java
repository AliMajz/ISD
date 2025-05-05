package com.Gymweb.gymweb.dto;

import com.Gymweb.gymweb.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingDto {

    private Long id;
    private int ratingValue;
    private String comment;
    private Long memberId;
    private Long coachId;

    public static RatingDto toDto(Rating rating) {
        return RatingDto.builder()
                .id(rating.getId())
                .ratingValue(rating.getRatingValue())
                .comment(rating.getComment())
                .memberId(rating.getMember().getId())  // Assuming Rating has a Member entity
                .coachId(rating.getCoach().getId())    // Assuming Rating has a Coach entity
                .build();
    }
}
