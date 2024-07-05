package com.Garbage.projectColection.controller;

import com.Garbage.projectColection.model.PaymentEntity;
import com.Garbage.projectColection.model.ScheduleEntity;
import com.Garbage.projectColection.model.UserEntity;
import com.Garbage.projectColection.model.dto.PaymentDto;
import com.Garbage.projectColection.services.PaymentService;
import com.Garbage.projectColection.services.ScheduleService;
import com.Garbage.projectColection.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/payment")
public class PaymentController {
    private ScheduleService scheduleService;
    private UserService userService;
    private PaymentService paymentService;
    private ModelMapper modelMapper;

    public PaymentController(ScheduleService scheduleService, UserService userService, PaymentService paymentService, ModelMapper modelMapper){
        this.scheduleService = scheduleService;
        this.userService = userService;
        this.paymentService = paymentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<PaymentDto> createPayments(@RequestBody PaymentDto paymentDto) {
        Optional<ScheduleEntity> scheduleEntity = scheduleService.getScheduleById(paymentDto.getSchedule().getScheduleID());
        Optional<UserEntity> userEntity = userService.getUserById(paymentDto.getUser().getUserID());

        if (scheduleEntity.isPresent() && userEntity.isPresent()) {
            PaymentEntity paymentEntity = modelMapper.map(paymentDto, PaymentEntity.class);
            paymentEntity.setSchedule(scheduleEntity.get());
            paymentEntity.setUser(userEntity.get());
            PaymentEntity savedPayment = paymentService.savePayment(paymentEntity);
            return new ResponseEntity<>(modelMapper.map(savedPayment, PaymentDto.class), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(path = "/all")
    public ResponseEntity<List<PaymentDto>> getAllPayments(){
        List<PaymentEntity> paymentEntities = paymentService.getAllPayments();
        List<PaymentDto> paymentDto = paymentEntities.stream()
                .map(user -> modelMapper.map(user, PaymentDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentDto> getPaymentsById(@PathVariable("paymentID") Long paymentID){
        Optional<PaymentEntity> paymentEntity = paymentService.getPaymentsById(paymentID);
        return paymentEntity.map(entity -> new ResponseEntity<>(modelMapper.map(entity,PaymentDto.class),HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PaymentDto> updatePayment(
            @PathVariable("paymentID") Long paymentID, @RequestBody PaymentDto paymentDto){
        if(!paymentService.isExist(paymentID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            paymentDto.setPayID(paymentID);
            PaymentEntity paymentEntity = modelMapper.map(paymentDto, PaymentEntity.class);
            PaymentEntity savedpaymentEntity = paymentService.savePayment(paymentEntity);
            return new ResponseEntity<>(modelMapper.map(savedpaymentEntity, PaymentDto.class), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletePayments(@PathVariable("paymentID") Long paymentID) {
        paymentService.deletePayments(paymentID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
