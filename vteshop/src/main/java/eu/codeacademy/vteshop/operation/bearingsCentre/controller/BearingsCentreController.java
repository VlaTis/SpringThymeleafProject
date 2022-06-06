package eu.codeacademy.vteshop.operation.bearingsCentre.controller;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.operation.operationStation.service.OperationStationService;
import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderStatusDto;
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
public class BearingsCentreController {

    private final ProductionOrderService productionOrderService;
    private final OperationStationService operationStationService;
    private final ProductService productService;


    @GetMapping("/bearings_centre")
    public String getOrders(Model model){
        model.addAttribute("operationStation", operationStationService.getOperationStations().get(1));
        model.addAttribute("productionOrders", productionOrderService.getFilteredProductionOrders(OperationStationDto.builder()
                .name("Bearings production").build(),
                ProductionOrderStatusDto.builder().name("Ready for Production").build()));
        model.addAttribute("currentOrder", productionOrderService.getFilteredOrdersByStatus(ProductionOrderStatusDto.builder().name("In Progress").build()));

        return "production/bearings";
    }





}
