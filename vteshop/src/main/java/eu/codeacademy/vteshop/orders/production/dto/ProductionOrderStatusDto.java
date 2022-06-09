package eu.codeacademy.vteshop.orders.production.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductionOrderStatusDto {

    private String name;
}
