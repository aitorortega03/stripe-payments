package com.stripe.stripe_payments.controller;

import com.stripe.stripe_payments.common.constants.ApiPathConstants;
import com.stripe.stripe_payments.common.dto.CheckoutRequest;
import com.stripe.stripe_payments.common.dto.CheckoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.STRIPE_ROUTE)
public interface StripeApi {

  @PostMapping("/webhook")
  ResponseEntity<Void> stripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String stripeHeader);

  @PostMapping("/checkout")
  ResponseEntity<CheckoutResponse> createCheckout(@RequestBody @Valid CheckoutRequest checkoutRequest);
}
