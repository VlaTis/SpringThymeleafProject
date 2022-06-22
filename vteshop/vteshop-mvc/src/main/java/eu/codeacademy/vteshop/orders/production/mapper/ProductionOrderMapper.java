package eu.codeacademy.vteshop.orders.production.mapper;

import eu.codeacademy.vteshop.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.orders.production.entity.ProductionOrder;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderMapper {

    public ProductionOrderDto mapTo(ProductionOrder productionOrder){
        return ProductionOrderDto.builder()
                .name(productionOrder.getName())
                .quantity(productionOrder.getQuantity())
                .productUUID(productionOrder.getProduct().getProductId())
                .productName(productionOrder.getProduct().getName())
                .order_status(productionOrder.getProductionOrderStatus().getName())
                .build();
    }

}
