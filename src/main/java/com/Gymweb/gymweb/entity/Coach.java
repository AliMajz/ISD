package com.Gymweb.gymweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "coach")
@EqualsAndHashCode(callSuper=false)
public class Coach extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String specialty;
    private String createdBy;
    private String updatedBy;
    @Column(columnDefinition = "TEXT")
    private String photo;

//    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Classes> classes;
}
