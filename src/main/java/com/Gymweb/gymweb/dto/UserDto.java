package com.Gymweb.gymweb.dto;

import com.Gymweb.gymweb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFname())
                .lastName(user.getLname())
                .email(user.getEmail())
                .phoneNumber(String.valueOf(user.getPhoneNb()))
                .role(user.getRole().name())
                .build();
    }
}