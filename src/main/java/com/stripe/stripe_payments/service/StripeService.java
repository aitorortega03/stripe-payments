package com.stripe.stripe_payments.service;

import com.stripe.model.Event;

public interface StripeService {

  void manageWebhook(Event event);

  Event constructEvent(String payload, String stripeHeader);
}
