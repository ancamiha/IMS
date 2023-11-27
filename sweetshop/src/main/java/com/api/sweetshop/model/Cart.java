package com.api.sweetshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
    private Long id;
    private Long userId;
    @ElementCollection
    private List<Long> products;

    public Cart() {
    }

    public Cart(Long userId, List<Long> products) {
        this.userId = userId;
        this.products = products;
    }

    public boolean addProductId(Long productId) {
        if (products.contains(productId))
            return false;
        products.add(productId);
        return true;
    }
}
