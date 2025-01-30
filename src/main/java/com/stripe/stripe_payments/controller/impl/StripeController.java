package com.stripe.stripe_payments.controller.impl;

import com.stripe.model.Event;
import com.stripe.stripe_payments.controller.StripeApi;
import com.stripe.stripe_payments.service.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeController implements StripeApi {

  private final StripeService stripeService;

  public StripeController(StripeService stripeService) {
    this.stripeService = stripeService;
  }

  @Override
  public ResponseEntity<Void> stripeWebhook(String payload, String stripeHeader) {
    Event event = stripeService.constructEvent(payload, stripeHeader);
    stripeService.manageWebhook(event);
    return ResponseEntity.noContent().build();
  }
}
