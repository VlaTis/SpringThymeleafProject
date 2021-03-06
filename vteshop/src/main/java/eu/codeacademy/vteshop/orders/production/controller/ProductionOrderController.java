package eu.codeacademy.vteshop.orders.production.controller;

import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.orders.production.service.ProductionOrderService;
import eu.codeacademy.vteshop.orders.production.service.ProductionOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;
    private final ProductService productService;
    private final ProductionOrderStatusService statusService;

    public static final String ORDERS_ROOT_PATH = "/orders/production";
    public static final String ORDER_CREATE_PATH = "/orders/production_order";
    public static final String ORDERS_DELETE_PATH = "/orders/production/delete";


    @GetMapping(ORDER_CREATE_PATH)
    public String createOrderForm(Model model) {
        model.addAttribute("orderDto", ProductionOrderDto.builder().build());
        model.addAttribute("productList", productService.getProducts());
        model.addAttribute("orderStatusList", statusService.getOrderStatuses());

        return "orders/production_order";
    }

    @PostMapping(ORDER_CREATE_PATH)
    public String createOrder(Model model, @Valid @ModelAttribute("orderDto") ProductionOrderDto orderDto, BindingResult errors,
                              RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            model.addAttribute("productList", productService.getProducts());
            model.addAttribute("orderStatusList", statusService.getOrderStatuses());

            return "orders/production_order";
        }

        productionOrderService.addProductionOrder(orderDto);
        redirectAttributes.addAttribute("message", "product.create.success");
        return "redirect:" + ORDER_CREATE_PATH;
    }

    @GetMapping("/orders/production_order/{orderName}/update")
    public String getUpdateOrder(Model model, @PathVariable("orderName") String orderName) {
        model.addAttribute("orderDto", productionOrderService.getProductionOrderDtoByName(orderName));
        model.addAttribute("productList", productService.getProducts());
        model.addAttribute("orderStatusList", statusService.getOrderStatuses());

        return "orders/production_order";
    }

    @PostMapping("/orders/production_order/{orderName}/update")
    public String getUpdateOrder(Model model, @Valid @ModelAttribute("orderDto") ProductionOrderDto orderDto, BindingResult errors, @PathVariable String orderName) {
        if (errors.hasErrors()) {
            model.addAttribute("productList", productService.getProducts());
            model.addAttribute("orderStatusList", statusService.getOrderStatuses());

            return "orders/production_order";
        }
        productionOrderService.updateOrder(orderDto);
        return "redirect:" + ORDERS_ROOT_PATH;
    }


    @GetMapping(ORDERS_ROOT_PATH)
    public String getOrders(
            Model model, @PageableDefault(size = 8, sort = {"productionOrderStatus"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("productionOrdersPaginated", productionOrderService.getProductionOrdersPaginated(pageable));

        return "orders/production_orders";
    }


    @PostMapping(ORDERS_DELETE_PATH)
    public String deleteOrder(@RequestParam String orderName) {
        productionOrderService.deleteOrder(orderName);

        return "redirect:" + ORDERS_ROOT_PATH;
    }


}
