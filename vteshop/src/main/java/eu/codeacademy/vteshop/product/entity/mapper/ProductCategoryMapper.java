package eu.codeacademy.vteshop.product.entity.mapper;

import eu.codeacademy.vteshop.product.dto.ProductCategoryDto;
import eu.codeacademy.vteshop.product.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {
    public ProductCategoryDto mapTo(ProductCategory productCategory){
        return ProductCategoryDto.builder()
                .name(productCategory.getName())
                .build();
    }
}
