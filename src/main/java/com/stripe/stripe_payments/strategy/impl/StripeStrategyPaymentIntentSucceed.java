package com.stripe.stripe_payments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.stripe_payments.common.enums.StripeEvent;
import com.stripe.stripe_payments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

@Component
public class StripeStrategyPaymentIntentSucceed implements StripeStrategy {
  @Override
  public boolean isApplicable(Event event) {
    return StripeEvent.PAYMENT_INTENT_SUCCEED.value.equals(event.getType());
  }

  @Override
  public Event process(Event event) {
    return null;
  }
}
