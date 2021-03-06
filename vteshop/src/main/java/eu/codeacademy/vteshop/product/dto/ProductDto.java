package eu.codeacademy.vteshop.product.dto;

import eu.codeacademy.vteshop.product.validator.OperationStationValid;
import eu.codeacademy.vteshop.product.validator.ProductCategoryValid;
import eu.codeacademy.vteshop.product.validator.ProductStatusValid;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder

public class ProductDto {

    private UUID productId;

    @NotBlank(message = "{validate.name.blank}")
    @Size(
            min = 3,
            max = 50,
            message = "{validate.name.size}"
    )
    private String name;

    @PositiveOrZero
    @Max(value = 1000)
    @NotNull
    private Integer quantity;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotBlank
    private String description;

    @OperationStationValid
    private String operationStationName;

    @ProductStatusValid
    private String productStatusName;

    @ProductCategoryValid
    private String productCategoryName;


}
