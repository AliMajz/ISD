package com.Gymweb.gymweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "members")
public class Member extends User implements UserDetails{

    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Boolean pt;

    @Enumerated(EnumType.STRING)
    private Membership membership;

    @ElementCollection(targetClass = RelaxingAreas.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "member_relaxing_areas", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "relaxing_area")
    private List<RelaxingAreas> relaxingAreas;

    @ElementCollection
    @CollectionTable(name = "member_schedules", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "schedule_id")
    private List<Schedule> schedules;

}
