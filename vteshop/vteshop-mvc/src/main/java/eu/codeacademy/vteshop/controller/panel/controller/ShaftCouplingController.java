package eu.codeacademy.vteshop.controller.panel.controller;

import eu.codeacademy.vteshop.common.station.dto.OperationStationDto;
import eu.codeacademy.vteshop.common.station.service.OperationStationService;
import eu.codeacademy.vteshop.common.product.service.ProductService;
import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.common.orders.production.service.ProductionOrderService;
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
    public static final String OP_SC_ST_PATH = "/op/shaft_coupling";
    public static final String OP_SC_START_PATH = "/op/shaft_coupling/start";
    public static final String OP_SC_FINISH_PATH = "/op/shaft_coupling/finish";
    public static final String OP_SC_CANCEL_PATH = "/op/shaft_coupling/cancel";


    @GetMapping(OP_SC_ST_PATH)
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

    @PostMapping(OP_SC_START_PATH)
    public String startProductionOrder(@RequestParam String productionOrderDtoName){
        OperationStationDto operationStationDto =  operationStationService.getOperationStationByName(SHAFT_COUPLING);
        operationStationDto.setStatus_name(STATION_STATUS_BUSY);
        operationStationService.updateOperationStationStatus(operationStationDto);
        productionOrderService.updateProductionOrderStatus(productionOrderDtoName, ProductionOrderStatusDto.builder()
                .name(ORDER_STATUS_PROGRESS).
                build());

        return "redirect:" + OP_SC_ST_PATH;
    }

    @PostMapping(OP_SC_FINISH_PATH)
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

        return "redirect:" + OP_SC_ST_PATH;
    }

    @PostMapping(OP_SC_CANCEL_PATH)
    public  String cancelProductionOrder(@RequestParam String productionOrderDtoName){
        OperationStationDto operationStationDto = operationStationService.getOperationStationByName(SHAFT_COUPLING);
        operationStationDto.setStatus_name(STATION_STATUS_IDLE);
        operationStationService.updateOperationStationStatus(operationStationDto);
        productionOrderService.updateProductionOrderStatus(productionOrderDtoName,
                ProductionOrderStatusDto.builder()
                        .name(ORDER_STATUS_READY)
                        .build());

        return "redirect:" + OP_SC_ST_PATH;
    }

}
