package com.stripe.stripe_payments.controller;

import com.stripe.stripe_payments.common.constants.ApiPathConstants;
import com.stripe.stripe_payments.common.dto.AuthResponseDTO;
import com.stripe.stripe_payments.common.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

  @PostMapping
  ResponseEntity<AuthResponseDTO> createUser(@RequestBody @Valid UserRequest userRequest);
}
