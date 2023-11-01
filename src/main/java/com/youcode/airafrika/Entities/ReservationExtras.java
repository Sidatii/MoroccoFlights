package com.youcode.airafrika.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reservationExtras")
public class ReservationExtras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    protected Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "extra_id")
    protected Extra extra;
}
