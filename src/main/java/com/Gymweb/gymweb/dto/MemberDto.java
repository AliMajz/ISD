package com.Gymweb.gymweb.dto;


import com.Gymweb.gymweb.entity.Coach;
import com.Gymweb.gymweb.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private LocalDate birthdayDate;
    private String gender;
    private LocalDate startDate;
    private LocalDate endDate;
    private String membership;

    private List<String> relaxingAreas;
    private List<String> classNames;

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .firstName(member.getFname())
                .lastName(member.getLname())
                .email(member.getEmail())
                .phoneNumber(String.valueOf(member.getPhoneNb()))
                .birthdayDate(member.getBirthdayDate())
                .gender(member.getGender())
                .startDate(member.getStartDate())
                .endDate(member.getEndDate())
                .membership(member.getMembership() != null ? member.getMembership().name() : null)
                .relaxingAreas(member.getRelaxingAreas() != null
                        ? member.getRelaxingAreas().stream().map(Enum::name).toList()
                        : null)
                .classNames(member.getClassNames())
                .build();
    }
}
