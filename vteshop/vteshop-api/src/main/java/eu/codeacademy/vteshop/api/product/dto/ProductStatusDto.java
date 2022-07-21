package eu.codeacademy.vteshop.api.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStatusDto {
    private String name;
}
