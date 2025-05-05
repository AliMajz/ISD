package com.Gymweb.gymweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Coach entity
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    // Many-to-One relationship with Classes entity
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes; // Reference to the Classes entity (not an enum)

    private Date startTime;
    private Date endTime;

    // A list of days for the schedule
    @ElementCollection(targetClass = Days.class)
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Enumerated(EnumType.STRING)
    private List<Days> days;


}
