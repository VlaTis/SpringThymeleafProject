package eu.codeacademy.vteshop.product.mapper;

import eu.codeacademy.vteshop.operationStation.mapper.OperationStationMapper;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductStatusMapper productStatusMapper;
    private final OperationStationMapper operationStationMapper;

    public ProductDto mapTo(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantityInStock())
                .price(product.getPrice())
                .description(product.getDescription())
                .productCategoryDto(productCategoryMapper.mapTo(product.getProductCategory()))
                .productStatusDto(productStatusMapper.mapTo(product.getProductStatus()))
                .operationStationDto(operationStationMapper.mapTo(product.getOperationStation()))
                .build();
    }
}
