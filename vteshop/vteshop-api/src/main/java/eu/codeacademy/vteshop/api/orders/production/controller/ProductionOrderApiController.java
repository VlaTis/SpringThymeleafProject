package eu.codeacademy.vteshop.api.orders.production.controller;


import eu.codeacademy.vteshop.api.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.api.orders.production.service.ProductionOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/production_orders")
@Api(tags = "Production Orders Controller")
public class ProductionOrderApiController {

    private final ProductionOrderService productionOrderService;

    @GetMapping()
    @ApiOperation(
            value = "Get all production orders",
            notes = "Production Orders")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public List<ProductionOrderDto> getProductionOrders() {
        return productionOrderService.getAllProductionOrders();
    }

    @GetMapping(path = "/station")
    @ApiOperation(
            value = "Get all production orders by station",
            notes = "Production Orders by station")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public List<ProductionOrderDto> getProductionOrdersByStationName(@RequestParam String stationName) {
        return productionOrderService.getFilteredOrdersByStationName(stationName);
    }


    @PostMapping
    @ApiOperation(value = "Create order", httpMethod = "POST")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createProductionOrder(@Valid @RequestBody ProductionOrderDto productionOrderDto) {
       productionOrderService.addProductionOrder(productionOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(
            value = "Update Production order",
            notes = "Update production order"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateOrder(@Valid @RequestBody ProductionOrderDto orderDto){
        if(productionOrderService.updateOrder(orderDto)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }

//    @PutMapping
//    @ApiOperation(
//            value = "Update Prodcution order status",
//            notes = "Update production order status"
//    )
//    public ResponseEntity<Void> updateOrderStatus(@Valid @RequestParam){
//        if(productionOrderService.updateProductionOrderStatus(statusDto)){
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//

    @DeleteMapping(path = "/{orderName}",
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public void deleteProductionOrder(@PathVariable("orderName") String orderName) {
        productionOrderService.deleteOrder(orderName);
    }




}
