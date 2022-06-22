package eu.codeacademy.vteshop.common.cart.dto;


import eu.codeacademy.vteshop.common.product.dto.ProductDto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CartDto {

    private final List<CartItem> cartItems = new ArrayList<>();

    public void add(ProductDto productDto) {
        cartItems.add(CartItem.builder()
                .productDto(productDto)
                .quantity(1)
                .build());
    }

    public BigDecimal getCartTotalPrice() {
        return cartItems.stream() // [CartItem1, CartItem2, ...]
                .map(CartItem::getItemTotalPrice) // Stream<BigDecimal> // 1, 4, 6.0, ...
                .reduce(BigDecimal.ZERO,  BigDecimal::add);
    }

    public Integer getCartTotalQuantity() {
        return cartItems.stream()
                .map(CartItem::getQuantity)
                .reduce(0,  Integer::sum);
    }

}