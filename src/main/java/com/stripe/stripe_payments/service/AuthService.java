package com.stripe.stripe_payments.service;

import com.stripe.stripe_payments.common.dto.AuthResponseDTO;
import com.stripe.stripe_payments.common.dto.UserRequest;

public interface AuthService {

  AuthResponseDTO createUser(UserRequest userRequest);
}
