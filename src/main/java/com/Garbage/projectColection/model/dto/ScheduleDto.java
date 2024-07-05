package com.Garbage.projectColection.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private Long scheduleID;
    private String collectionDay;
    private LocalDate collectionDate;
    private LocalTime collectionTime;
}
