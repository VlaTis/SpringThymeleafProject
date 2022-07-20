package eu.codeacademy.vteshop.api.controller.orders.production;


import eu.codeacademy.vteshop.api.dto.ProductResponse;
import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.common.orders.production.service.ProductionOrderService;
import eu.codeacademy.vteshop.common.product.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
    public List<ProductionOrderDto> getProductionOrders() {
        return productionOrderService.getAllProductionOrders();
    }

    @GetMapping(path = "/station")
    @ApiOperation(
            value = "Get all production orders by station",
            notes = "Production Orders by station")
    public List<ProductionOrderDto> getProductionOrdersByStationName(@RequestParam String stationName) {
        return productionOrderService.getFilteredOrdersByStationName(stationName);
    }


    @PostMapping
    @ApiOperation(value = "Create order", httpMethod = "POST")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductionOrderDto productionOrderDto) {
       productionOrderService.addProductionOrder(productionOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(
            value = "Update Prodcution order",
            notes = "Update production order"
    )
    public ResponseEntity<Void> updateOrder(@Valid @RequestBody ProductionOrderDto orderDto){
        if(productionOrderService.updateOrder(orderDto)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{orderName}",
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public void deleteProduct(@PathVariable("orderName") String orderName) {
        productionOrderService.deleteOrder(orderName);
    }




}
