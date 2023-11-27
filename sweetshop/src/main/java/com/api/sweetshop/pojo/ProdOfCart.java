package com.api.sweetshop.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdOfCart {
    private Long userId;
    private Long product;

    public ProdOfCart(Long userId, Long product) {
        this.userId = userId;
        this.product = product;
    }
}
