package com.Gymweb.gymweb.dto;


import com.Gymweb.gymweb.entity.*;
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

    private Boolean pt;
    private LocalDate startDate;
    private LocalDate endDate;
    private Membership membership;
    private List<RelaxingAreas> relaxingAreas;
    private List<Long> scheduleIds;

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .firstName(member.getFname())
                .lastName(member.getLname())
                .email(member.getEmail())
                .phoneNumber(String.valueOf(member.getPhoneNb()))
                .birthdayDate(member.getBirthdayDate())
                .gender(member.getGender())
                .pt(member.getPt())
                .startDate(member.getStartDate())
                .endDate(member.getEndDate())
                .membership(member.getMembership())
                .relaxingAreas(member.getRelaxingAreas())
                //.schedules(member.getSchedules())
                .build();
    }
}
