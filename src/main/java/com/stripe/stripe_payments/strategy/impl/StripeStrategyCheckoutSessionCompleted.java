package com.stripe.stripe_payments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.stripe_payments.common.entity.Payment;
import com.stripe.stripe_payments.common.enums.StripeEvent;
import com.stripe.stripe_payments.repository.PaymentRepository;
import com.stripe.stripe_payments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeStrategyCheckoutSessionCompleted implements StripeStrategy {

  private final PaymentRepository paymentRepository;

  public StripeStrategyCheckoutSessionCompleted(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public boolean isApplicable(Event event) {
    return StripeEvent.CHECKOUT_SESSION_COMPLETED.value.equals(event.getType());
  }

  @Override
  public Event process(Event event) {
    Session session = this.deserialize(event);
    return Optional.of(event)
        .map(givenEvent -> paymentRepository.findByPaymentIntentId(session.getPaymentIntent()))
        .map(payment -> setProductId(payment, session.getMetadata().get("product_id")))
        .map(paymentRepository::save)
        .map(given -> event)
        .orElseThrow(() -> new RuntimeException("Error processing"));
  }

  private Payment setProductId(Payment payment, String productId) {
    payment.setProductId(productId);
    payment.setType(StripeEvent.CHECKOUT_SESSION_COMPLETED);
    return payment;
  }

  private Session deserialize(Event event) {
    return Session.GSON.fromJson(
        event.getDataObjectDeserializer().getRawJson(),
        Session.class
    );
  }
}
