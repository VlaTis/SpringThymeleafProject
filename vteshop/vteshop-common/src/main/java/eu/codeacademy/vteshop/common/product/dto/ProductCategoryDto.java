package eu.codeacademy.vteshop.common.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDto {
    private String name;
}
