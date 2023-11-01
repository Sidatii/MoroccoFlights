package com.youcode.airafrika.Entities;

import com.youcode.airafrika.Enums.ClassType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flightPaths")
public class FlightPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "departure_date")
    protected LocalDate departureDate;
    @Column(name = "arrival_date")
    protected LocalDate arrivalDate;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    protected Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrive_airport_id")
    protected Airport arriveAirport;
    @OneToOne
    @JoinColumn(name = "flight_id")
    protected Flight flight;
    @Enumerated(EnumType.STRING)
    @Column(name = "class_type")
    protected ClassType classType;
    @OneToMany
    protected List<StopOver> stopOvers;

    @Override
    public String toString() {
        return "FlightPath{" +
                "id=" + id +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureAirport=" + departureAirport +
                ", arriveAirport=" + arriveAirport +
                ", flight=" + flight +
                ", classType=" + classType +
                '}';
    }
}
