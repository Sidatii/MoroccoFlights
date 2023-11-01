package com.youcode.airafrika.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stopOvers")
public class StopOver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "duration")
    protected LocalTime duration;
    @ManyToOne
    @JoinColumn(name = "airport_id")
    protected Airport airport;
}
