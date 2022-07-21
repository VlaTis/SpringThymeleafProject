package eu.codeacademy.vteshop.api.orders.sales.mapper;

import eu.codeacademy.vteshop.api.orders.sales.dto.SalesOrderDto;
import eu.codeacademy.vteshop.jpa.orders.sales.entity.SalesOrder;

import org.springframework.stereotype.Component;

@Component
public class SalesOrderMapper {

    public SalesOrderDto mapTo(SalesOrder salesOrder){
        return SalesOrderDto.builder()
                .name(salesOrder.getName())
                .totalPrice(salesOrder.getTotalPrice())
                .build();
    }
}
