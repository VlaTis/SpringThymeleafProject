package eu.codeacademy.vteshop.productionOrder.controller;

import eu.codeacademy.vteshop.operation.operationStation.service.OperationStationService;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrderStatus;
import eu.codeacademy.vteshop.productionOrder.service.ProductionOrderService;
import eu.codeacademy.vteshop.productionOrder.service.ProductionOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;
    private final ProductService productService;
    private final ProductionOrderStatusService statusService;



   @GetMapping("/orders/production_order")
   public String createOrderForm(Model model){
       model.addAttribute("orderDto", ProductionOrderDto.builder().build());
       model.addAttribute("productList", productService.getProducts());
       model.addAttribute("orderStatusList", statusService.getOrderStatuses());

       return "orders/production_order";
   }

    @PostMapping("/orders/production_order")
    public String createOrder(Model model, @Valid @ModelAttribute("orderDto") ProductionOrderDto orderDto, BindingResult errors,
                              RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()){
            model.addAttribute("productList", productService.getProducts());
            model.addAttribute("orderStatusList", statusService.getOrderStatuses());

            return "orders/production_order";
        }

        productionOrderService.addProductionOrder(orderDto);
        redirectAttributes.addAttribute("message", "product.create.success");
        return "redirect:" + "/orders/production_order";
    }

    @GetMapping("/orders/production_order/{orderName}/update")
    public String getUpdateOrder(Model model, @PathVariable("orderName") String orderName) {
        model.addAttribute("orderDto", productionOrderService.getProductionOrderDtoByName(orderName));
        model.addAttribute("productList", productService.getProducts());
        model.addAttribute("orderStatusList", statusService.getOrderStatuses());

        return "orders/production_order";
    }

    @PostMapping("/orders/production_order/{orderName}/update")
    public String getUpdateOrder(Model model, @Valid @ModelAttribute("orderDto") ProductionOrderDto orderDto,BindingResult errors, @PathVariable String orderName){
       if(errors.hasErrors()){
           model.addAttribute("productList", productService.getProducts());
           model.addAttribute("orderStatusList", statusService.getOrderStatuses());

           return "orders/production_order";
       }
       productionOrderService.updateOrder(orderDto);
        return "redirect:" + "/orders/production";
    }



    @GetMapping("/orders/production")
    public String getOrders(
            Model model, @PageableDefault(size = 8, sort = {"productionOrderStatus"}, direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("productionOrdersPaginated",productionOrderService.getProductionOrdersPaginated(pageable));

        return "orders/production_orders";
    }







}
