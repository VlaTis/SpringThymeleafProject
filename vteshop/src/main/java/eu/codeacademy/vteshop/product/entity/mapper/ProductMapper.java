package eu.codeacademy.vteshop.product.entity.mapper;

import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto mapTo(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantityInStock())
                .price(product.getPrice())
                .description(product.getDescription())
                .productCategory(product.getProductCategory().getName())
                .availabilityStatus(product.getProductStatus().getName())
                .canBeMadeIn(product.getOperationStation().getName())
                .build();
    }
}
