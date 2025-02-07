package com.stripe.stripe_payments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.stripe_payments.common.entity.Payment;
import com.stripe.stripe_payments.common.enums.StripeEvent;
import com.stripe.stripe_payments.repository.PaymentRepository;
import com.stripe.stripe_payments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeStrategyPaymentIntentSucceed implements StripeStrategy {

  private final PaymentRepository paymentRepository;

  public StripeStrategyPaymentIntentSucceed(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public boolean isApplicable(Event event) {
    return StripeEvent.PAYMENT_INTENT_SUCCEED.value.equals(event.getType());
  }

  @Override
  public Event process(Event event) {
    return Optional.of(event)
        .map(this::deserialize)
        .map(this::mapToEntity)
        .map(paymentRepository::save)
        .map(given -> event)
        .orElseThrow(() -> new RuntimeException("Error processing"));
  }

  private Payment mapToEntity(PaymentIntent paymentIntent) {
    return Payment.builder()
        .paymentIntentId(paymentIntent.getId())
        .customerId(paymentIntent.getCustomer())
        .amount(paymentIntent.getAmount())
        .currency(paymentIntent.getCurrency())
        .type(StripeEvent.PAYMENT_INTENT_SUCCEED)
        .build();
  }

  private PaymentIntent deserialize(Event event) {
    return PaymentIntent.GSON.fromJson(
        event.getDataObjectDeserializer().getRawJson(),
        PaymentIntent.class
    );
  }
}
