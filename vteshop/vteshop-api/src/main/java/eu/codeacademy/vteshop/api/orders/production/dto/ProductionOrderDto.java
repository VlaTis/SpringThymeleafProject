package eu.codeacademy.vteshop.api.orders.production.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.*;
import java.util.UUID;

@Data
@Builder
public class ProductionOrderDto {

    @NotBlank(message = "{validate.name.blank}")
    @Size(
            min = 3,
            max = 50,
            message = "{validate.name.size}"
    )
    private String name;

    @Positive
    @Max(value = 1000)
    @NotNull
    private Integer quantity;

    @NotNull
    private UUID productUUID;

    private String productName;

    @NotNull
    private String order_status;


}
