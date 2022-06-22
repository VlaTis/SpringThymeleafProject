package eu.codeacademy.vteshop.orders.sales.controller;

import eu.codeacademy.vteshop.orders.sales.service.SalesOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderService orderService;

    @GetMapping("/orders/sales")
    public String getSalesOrders(Model model){
        model.addAttribute("orders", orderService.getSalesOrders());

        return "orders/sales_orders";

    }
}
