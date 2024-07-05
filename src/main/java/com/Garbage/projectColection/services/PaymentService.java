package com.Garbage.projectColection.services;

import com.Garbage.projectColection.model.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    PaymentEntity savePayment(PaymentEntity payment);
    List<PaymentEntity> getAllPayments();
    Optional<PaymentEntity> getPaymentsById(Long paymentID);
    void  deletePayments(Long paymentID);
    boolean isExist(Long paymentID);
}
