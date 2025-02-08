package com.stripe.stripe_payments.controller.impl;

import com.stripe.stripe_payments.common.dto.AuthResponseDTO;
import com.stripe.stripe_payments.common.dto.UserRequest;
import com.stripe.stripe_payments.controller.AuthApi;
import com.stripe.stripe_payments.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public ResponseEntity<AuthResponseDTO> createUser(UserRequest userRequest) {
    return ResponseEntity.ok(authService.createUser(userRequest));
  }
}
