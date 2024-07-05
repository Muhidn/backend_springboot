package com.Garbage.projectColection.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleID;

    @Column(name = "collectionDay")
    private String collectionDay;

    @Column(name = "collectionDate")
    private LocalDate collectionDate;

    @Column(name = "collectionTime")
    private LocalTime collectionTime;

}
