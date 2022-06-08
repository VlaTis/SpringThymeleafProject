package eu.codeacademy.vteshop.productionOrder.controller;

import eu.codeacademy.vteshop.operation.operationStation.service.OperationStationService;
import eu.codeacademy.vteshop.productionOrder.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;
    private final OperationStationService operationStationService;

    @GetMapping("/orders/production")
    public String getOrders(
            Model model, @PageableDefault(size = 8, sort = {"productionOrderStatus"}, direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("productionOrdersPaginated",productionOrderService.getProductionOrdersPaginated(pageable));

        return "orders/production_orders";


    }




}
