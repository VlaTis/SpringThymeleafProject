package eu.codeacademy.vteshop.product.mapper;

import eu.codeacademy.vteshop.product.dto.ProductStatusDto;
import eu.codeacademy.vteshop.product.entity.ProductStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductStatusMapper {
    public ProductStatusDto mapTo(ProductStatus productStatus){
        return ProductStatusDto.builder()
                .name(productStatus.getName())
                .build();
    }
}
