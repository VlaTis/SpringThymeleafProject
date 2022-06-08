package eu.codeacademy.vteshop.operation.panel.controller;

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
import static eu.codeacademy.vteshop.helper.OperationConstants.*;


@Controller
@RequiredArgsConstructor
public class ShaftCouplingController {

    private final ProductionOrderService productionOrderService;
    private final OperationStationService operationStationService;
    private final ProductService productService;


    @GetMapping("/shaft_coupling")
    public String getOrders(Model model){
        OperationStationDto operationStationDto = operationStationService.getOperationStationByName(SHAFT_COUPLING);
        model.addAttribute("operationStation", operationStationDto);
        model.addAttribute("productionOrders", productionOrderService.getFilteredProductionOrders(operationStationDto,
                ProductionOrderStatusDto.builder().name(ORDER_STATUS_READY).build()));
        model.addAttribute("currentOrder", productionOrderService.getFilteredOrdersByStatusAndStation(ProductionOrderStatusDto.builder()
                .name(ORDER_STATUS_PROGRESS)
                .build(), operationStationDto));

        return "production/shaft_coupling";
    }

    @PostMapping("/shaft_coupling/start")
    public String startProductionOrder(@RequestParam String productionOrderDtoName){
        OperationStationDto operationStationDto =  operationStationService.getOperationStationByName(SHAFT_COUPLING);
        operationStationDto.setStatus_name(STATION_STATUS_BUSY);
        operationStationService.updateOperationStationStatus(operationStationDto);
        productionOrderService.updateProductionOrderStatus(productionOrderDtoName, ProductionOrderStatusDto.builder()
                .name(ORDER_STATUS_PROGRESS).
                build());

        return "redirect:" + "/shaft_coupling";
    }

    @PostMapping("/shaft_coupling/finish")
    public  String finishProductionOrder(@RequestParam String productionOrderDtoName){
        OperationStationDto operationStationDto = operationStationService.getOperationStationByName(SHAFT_COUPLING);
        operationStationDto.setStatus_name(STATION_STATUS_IDLE);
        ProductionOrderDto productionOrderDto = productionOrderService.getProductionOrderDtoByName(productionOrderDtoName);
        operationStationService.updateOperationStationStatus(operationStationDto);
        productionOrderService.updateProductionOrderStatus(productionOrderDtoName,
                ProductionOrderStatusDto.builder()
                        .name(ORDER_STATUS_FINISHED)
                        .build());
        productService.updateProductQuantity(productionOrderDto.getProductUUID(),
                productionOrderDto.getQuantity());

        return "redirect:" + "/shaft_coupling";
    }

    @PostMapping("/shaft_coupling/cancel")
    public  String cancelProductionOrder(@RequestParam String productionOrderDtoName){
        OperationStationDto operationStationDto = operationStationService.getOperationStationByName(SHAFT_COUPLING);
        operationStationDto.setStatus_name(STATION_STATUS_IDLE);
        operationStationService.updateOperationStationStatus(operationStationDto);
        productionOrderService.updateProductionOrderStatus(productionOrderDtoName,
                ProductionOrderStatusDto.builder()
                        .name(ORDER_STATUS_READY)
                        .build());

        return "redirect:" + "/shaft_coupling";
    }

}
