package com.Gymweb.gymweb.auth;

import com.Gymweb.gymweb.entity.Membership;
import com.Gymweb.gymweb.entity.RelaxingAreas;
import com.Gymweb.gymweb.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequest {
    private String fname;
    private String lname;
    private LocalDate birthdayDate;
    private String gender;
    private String email;
    private String password;
    private List<String> classname;
    private List<RelaxingAreas> relaxingAreas;
    private Membership membership;
    private String phoneNb;
    private Role role;
//    private LocalDate startDate;
//    private LocalDate endDate;
}
