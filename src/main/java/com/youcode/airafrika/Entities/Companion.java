package com.youcode.airafrika.Entities;

import com.youcode.airafrika.Enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "companions")
public class Companion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    protected Gender gender;
    @Column(name = "birth_date")
    protected LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    protected Reservation reservation;
}
