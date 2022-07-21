package eu.codeacademy.vteshop.api.product.exeption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ProductNotFoundException extends RuntimeException {

    private final UUID productId;
}
