package eu.codeacademy.vteshop.productionOrder.mapper;

import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderStatusMapper {


    public ProductionOrderStatusDto mapTo(ProductionOrderStatus orderStatus){
        return ProductionOrderStatusDto.builder()
                .name(orderStatus.getName())
                .build();
    }
}
