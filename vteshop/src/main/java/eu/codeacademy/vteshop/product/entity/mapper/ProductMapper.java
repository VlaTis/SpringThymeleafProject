package eu.codeacademy.vteshop.product.entity.mapper;

import eu.codeacademy.vteshop.operationStation.mapper.OperationStationMapper;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private ProductCategoryMapper productCategoryMapper;
    private ProductStatusMapper productStatusMapper;
    private OperationStationMapper operationStationMapper;

    public ProductDto mapTo(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantityInStock())
                .price(product.getPrice())
                .description(product.getDescription())
                .productCategory(productCategoryMapper.mapTo(product.getProductCategory()))
                .productStatusDto(productStatusMapper.mapTo(product.getProductStatus()))
                .operationStationDto(operationStationMapper.mapTo(product.getOperationStation()))
                .build();
    }
}
