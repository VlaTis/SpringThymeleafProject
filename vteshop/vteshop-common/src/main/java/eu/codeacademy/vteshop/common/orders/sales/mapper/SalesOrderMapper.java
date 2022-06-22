package eu.codeacademy.vteshop.common.orders.sales.mapper;

import eu.codeacademy.vteshop.common.orders.sales.dto.SalesOrderDto;
import eu.codeacademy.vteshop.orders.sales.entity.SalesOrder;
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
