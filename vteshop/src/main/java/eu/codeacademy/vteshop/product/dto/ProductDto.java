package eu.codeacademy.vteshop.product.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductDto {

    private UUID productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String description;
    private String canBeMadeIn;
    private String availabilityStatus;
    private String productCategory;


}
