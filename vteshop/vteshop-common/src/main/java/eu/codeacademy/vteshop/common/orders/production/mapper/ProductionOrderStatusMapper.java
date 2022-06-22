package eu.codeacademy.vteshop.common.orders.production.mapper;

import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.jpa.orders.production.entity.ProductionOrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderStatusMapper {


    public ProductionOrderStatusDto mapTo(ProductionOrderStatus orderStatus){
        return ProductionOrderStatusDto.builder()
                .name(orderStatus.getName())
                .build();
    }
}
