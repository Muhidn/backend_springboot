package com.Garbage.projectColection.services.Impl;

import com.Garbage.projectColection.Repository.PaymentRepository;
import com.Garbage.projectColection.model.PaymentEntity;
import com.Garbage.projectColection.services.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentEntity savePayment(PaymentEntity payment){
        return paymentRepository.save(payment);
    }

    @Override
    public List<PaymentEntity> getAllPayments(){
        return paymentRepository.findAll();
    }

    @Override
    public Optional<PaymentEntity> getPaymentsById(Long paymentID){
        return paymentRepository.findById(paymentID);
    }

    @Override
    public void  deletePayments(Long paymentID){
        paymentRepository.deleteById(paymentID);
    }

    @Override
    public boolean isExist(Long paymentID){
        return paymentRepository.existsById(paymentID);
    }
}
