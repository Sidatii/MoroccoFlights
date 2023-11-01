package com.youcode.airafrika.Entities;

import com.youcode.airafrika.Enums.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "planes")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "seats_count")
    protected Integer seatsCount;
    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    protected Model model;
    @Column(name = "construction_date")
    protected LocalDate constructionDate;
    @ManyToOne
    @JoinColumn(name = "company_id")
    protected Company company;
    @OneToMany(mappedBy = "plane")
    protected List<Seat> seats;
}
