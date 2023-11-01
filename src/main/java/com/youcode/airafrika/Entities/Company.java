package com.youcode.airafrika.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "founded_date")
    protected LocalDate foundedDate;
    @Column(name = "email")
    protected String email;
    @Column(name = "website")
    protected String website;
    @OneToMany(mappedBy = "company")
    protected List<Plane> planes;
}
