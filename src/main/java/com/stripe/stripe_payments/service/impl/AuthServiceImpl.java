package com.stripe.stripe_payments.service.impl;

import com.stripe.model.Customer;
import com.stripe.model.Product;
import com.stripe.stripe_payments.common.dto.AuthResponseDTO;
import com.stripe.stripe_payments.common.dto.UserRequest;
import com.stripe.stripe_payments.common.entity.User;
import com.stripe.stripe_payments.repository.UserRepository;
import com.stripe.stripe_payments.service.AuthService;
import com.stripe.stripe_payments.service.StripeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

  private final StripeService stripeService;

  private final UserRepository userRepository;

  public AuthServiceImpl(StripeService stripeService, UserRepository userRepository) {
    this.stripeService = stripeService;
    this.userRepository = userRepository;
  }

  @Override
  public AuthResponseDTO createUser(UserRequest userRequest) {
    return Optional.of(userRequest)
        .map(this::mapToEntity)
        .map(this::setUserCustomerAndProduct)
        .map(userRepository::save)
        .map(userModel -> AuthResponseDTO.builder()
            .customerId(userModel.getCustomerId())
            .productId(userModel.getProductId())
            .build())
        .orElseThrow(() -> new RuntimeException("Error creating user"));
  }

  private User setUserCustomerAndProduct(User user) {
    Customer createdCustomer = stripeService.createCustomer(user.getEmail());
    Product createdProduct = stripeService.createProduct(user.getUsername());
    stripeService.createPrice(createdProduct.getId());

    user.setCustomerId(createdCustomer.getId());
    user.setProductId(createdProduct.getId());
    return user;
  }

  private User mapToEntity(UserRequest userRequest) {
    return User.builder()
        .username(userRequest.getName())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .build();
  }
}
