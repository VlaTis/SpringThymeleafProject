package eu.codeacademy.vteshop.common.product.mapper;

import eu.codeacademy.vteshop.common.product.dto.ProductStatusDto;
import eu.codeacademy.vteshop.jpa.product.entity.ProductStatus;

import org.springframework.stereotype.Component;

@Component
public class ProductStatusMapper {
    public ProductStatusDto mapTo(ProductStatus productStatus){
        return ProductStatusDto.builder()
                .name(productStatus.getName())
                .build();
    }
}
