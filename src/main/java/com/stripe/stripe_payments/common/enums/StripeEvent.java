package com.stripe.stripe_payments.common.enums;

public enum StripeEvent {
    PAYMENT_INTENT_SUCCEED("payment_intent.succeeded"),
    CHECKOUT_SESSION_COMPLETED("checkout.session.completed");

    public final String value;

    StripeEvent(String value) {
        this.value = value;
    }
}
