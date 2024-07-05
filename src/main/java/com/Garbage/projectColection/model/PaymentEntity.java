package com.Garbage.projectColection.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scheduleID")
    private ScheduleEntity schedule;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @Column(name = "status")
    private String status;

}
