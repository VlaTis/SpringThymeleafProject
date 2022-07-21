package eu.codeacademy.vteshop.api.orders.sales.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SalesOrderDto {
    private String name;
    private BigDecimal totalPrice;
}
