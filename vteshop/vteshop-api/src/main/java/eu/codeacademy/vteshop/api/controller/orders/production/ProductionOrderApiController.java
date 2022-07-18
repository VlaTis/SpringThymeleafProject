package eu.codeacademy.vteshop.api.controller.orders.production;


import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.common.orders.production.service.ProductionOrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/production_orders")
@Api(tags = "Production Orders Controller")
public class ProductionOrderApiController {

    private final ProductionOrderService productionOrderService;

    @GetMapping()
    public List<ProductionOrderDto> getProductionOrders(){
        return productionOrderService.getAllProductionOrders();
    }
}
