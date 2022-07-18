package eu.codeacademy.vteshop.api.controller.orders.sales;


import eu.codeacademy.vteshop.common.orders.sales.dto.SalesOrderDto;
import eu.codeacademy.vteshop.common.orders.sales.service.SalesOrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales_orders")
@Api(tags = "Sales Orders Controller")
public class SalesOrderApiController {

    private final SalesOrderService salesOrderService;

    @GetMapping()
    public List<SalesOrderDto> getSalesOrders(){
        return salesOrderService.getSalesOrders();
    }
}
