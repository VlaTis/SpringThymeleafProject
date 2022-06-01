package eu.codeacademy.vteshop.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDto {
    private String name;
}
