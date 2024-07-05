package com.Garbage.projectColection.model.dto;


import com.Garbage.projectColection.model.ScheduleEntity;
import com.Garbage.projectColection.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long payID;
    private UserDto user;
    private ScheduleDto schedule;
    private Double amount;
    private LocalDate paymentDate;
    private String status;
}
