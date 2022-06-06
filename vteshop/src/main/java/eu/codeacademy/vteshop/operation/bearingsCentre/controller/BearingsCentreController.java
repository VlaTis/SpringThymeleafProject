package eu.codeacademy.vteshop.operation.bearingsCentre.controller;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.productionOrder.service.ProductionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BearingsCentreController {

    private final ProductionOrderService productionOrderService;


    @GetMapping("/bearings_centre")
    public String getOrders(Model model){
        model.addAttribute("productionOrders", productionOrderService.getFilteredProductionOrders(OperationStationDto.builder()
                .name("Bearings production").build(),
                ProductionOrderStatusDto.builder().name("Ready for Production").build()));

        return "production/bearings";
    }
}
