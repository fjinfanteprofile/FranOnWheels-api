package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private VehicleType type;

    private String model;

    private int year;

    @Column(name = "license_plate")
    private String licensePlate;

    private String gearbox;

    @Column(name = "displacement_cc")
    private int displacementCc;

}