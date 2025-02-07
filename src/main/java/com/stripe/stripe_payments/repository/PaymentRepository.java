package com.stripe.stripe_payments.repository;

import com.stripe.stripe_payments.common.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  Payment findByPaymentIntentId(String paymentId);
}
