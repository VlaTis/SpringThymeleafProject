package eu.codeacademy.vteshop.api.controller.orders.sales;


import eu.codeacademy.vteshop.common.orders.sales.dto.SalesOrderDto;
import eu.codeacademy.vteshop.common.orders.sales.service.SalesOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales_orders")
@Api(tags = "Sales Orders Controller")
public class SalesOrderApiController {

    private final SalesOrderService salesOrderService;

    @GetMapping()
    public List<SalesOrderDto> getSalesOrders() {
        return salesOrderService.getSalesOrders();
    }

    @PostMapping
    @ApiOperation(value = "Create order", httpMethod = "POST")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody SalesOrderDto salesOrderDto) {
        salesOrderService.addSalesOrder(salesOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
