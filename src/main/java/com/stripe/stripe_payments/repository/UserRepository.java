package com.stripe.stripe_payments.repository;

import com.stripe.stripe_payments.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
