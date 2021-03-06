package eu.codeacademy.vteshop.orders.production.mapper;

import eu.codeacademy.vteshop.orders.production.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.orders.production.entity.ProductionOrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderStatusMapper {


    public ProductionOrderStatusDto mapTo(ProductionOrderStatus orderStatus){
        return ProductionOrderStatusDto.builder()
                .name(orderStatus.getName())
                .build();
    }
}
