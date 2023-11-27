package com.api.sweetshop.repository;

import com.api.sweetshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByUserId(Long userId);

    Cart getByUserId(Long userId);
}
