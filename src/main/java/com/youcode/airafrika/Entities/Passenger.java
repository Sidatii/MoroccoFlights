package com.youcode.airafrika.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "passengers")
public class Passenger extends User {
    @OneToMany(mappedBy = "passenger")
    protected List<Reservation> reservations;
}
