package eu.codeacademy.vteshop.api.product.mapper;

import eu.codeacademy.vteshop.api.product.dto.ProductCategoryDto;
import eu.codeacademy.vteshop.jpa.product.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {
    public ProductCategoryDto mapTo(ProductCategory productCategory){
        return ProductCategoryDto.builder()
                .name(productCategory.getName())
                .build();
    }
}