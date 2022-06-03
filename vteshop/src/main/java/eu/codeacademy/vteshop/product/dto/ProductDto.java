package eu.codeacademy.vteshop.product.dto;

import eu.codeacademy.vteshop.operationStation.dto.OperationStationDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductDto {

    private UUID productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String description;
    private OperationStationDto operationStationDto;
    private ProductStatusDto productStatusDto;
    private ProductCategoryDto productCategoryDto;



}
