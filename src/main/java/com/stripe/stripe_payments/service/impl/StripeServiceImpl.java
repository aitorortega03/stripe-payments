package com.stripe.stripe_payments.service.impl;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import com.stripe.stripe_payments.service.StripeService;
import com.stripe.stripe_payments.strategy.StripeStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StripeServiceImpl implements StripeService {

  private final String endpointSecret;

  private final List<StripeStrategy> stripeStrategies;

  public StripeServiceImpl(@Value("${stripe.endpoint.secret}") String endpointSecret, List<StripeStrategy> stripeStrategies) {
    this.endpointSecret = endpointSecret;
    this.stripeStrategies = Collections.unmodifiableList(stripeStrategies);
  }

  @Override
  public void manageWebhook(Event event) {
    Optional.of(event)
        .map(this::processStrategy);
  }

  @Override
  public Event constructEvent(String payload, String stripeHeader) {
    try {
      return Webhook.constructEvent(payload, stripeHeader, endpointSecret);
    } catch (SignatureVerificationException e) {
      throw new RuntimeException(e);
    }
  }

  private Event processStrategy(Event event) {
    return stripeStrategies.stream()
        .filter(stripeStrategy -> stripeStrategy.isApplicable(event))
        .findFirst()
        .map(stripeStrategy -> stripeStrategy.process(event))
        .orElseGet(Event::new);
  }
}
