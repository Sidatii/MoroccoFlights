package com.youcode.airafrika.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    protected Passenger passenger;
    @ManyToOne
    protected Flight flight;
    @OneToMany(mappedBy = "reservation")
    protected List<Companion> companions;
    protected double price;
}
