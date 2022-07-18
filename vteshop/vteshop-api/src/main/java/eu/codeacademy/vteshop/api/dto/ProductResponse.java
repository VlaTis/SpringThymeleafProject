package eu.codeacademy.vteshop.api.dto;

import eu.codeacademy.vteshop.common.product.dto.ProductDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductResponse {


    @ApiModelProperty(notes = "Products list", required = true, allowEmptyValue = false)
    private List<ProductDto> products;
}
