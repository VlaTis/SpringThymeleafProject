package eu.codeacademy.vteshop.operation.bearingsCentre.controller;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.operation.operationStation.service.OperationStationService;
import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.productionOrder.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BearingsCentreController {

    private final ProductionOrderService productionOrderService;
    private final OperationStationService operationStationService;
    private final ProductService productService;


    @GetMapping("/bearings_centre")
    public String getOrders(Model model){
        OperationStationDto operationStationDto = operationStationService.getOperationStations().get(1);
        model.addAttribute("operationStation", operationStationDto);
        model.addAttribute("productionOrders", productionOrderService.getFilteredProductionOrders(operationStationDto,
                ProductionOrderStatusDto.builder().name("Ready for Production").build()));
        model.addAttribute("currentOrder", productionOrderService.getFilteredOrdersByStatus(ProductionOrderStatusDto.builder().name("In Progress").build()));

        return "production/bearings";
    }

    @PostMapping("/bearings_centre/start")
    public String startProductionOrder(@RequestParam String productionOrderDtoName){
        OperationStationDto operationStationDto = operationStationService.getOperationStations().get(1);
        operationStationDto.setStatus_name("Busy");
        operationStationService.updateOperationStationStatus(operationStationDto);
        productionOrderService.updateProductionOrderStatus(productionOrderDtoName, ProductionOrderStatusDto.builder().name("In Progress").build());
        operationStationService.getOperationStations().get(1).setStatus_name("Busy");

        return "redirect:" + "/bearings_centre";
    }





}
