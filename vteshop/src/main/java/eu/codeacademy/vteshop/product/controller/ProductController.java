package eu.codeacademy.vteshop.product.controller;

import eu.codeacademy.vteshop.operation.station.service.OperationStationService;
import eu.codeacademy.vteshop.orders.production.service.ProductionOrderService;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.service.ProductCategoryService;
import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.product.service.ProductStatusService;
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
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductStatusService productStatusService;
    private final ProductCategoryService productCategoryService;
    private final OperationStationService operationStationService;
    private final ProductionOrderService productionOrderService;


    private static final String PRODUCT_ROOT_PATH = "/product";
    public static final String PUBLIC_PRODUCTS_ROOT_PATH = "/public/products";
    private static final String PRODUCT_DELETE_PATH = "/products/delete";


    @GetMapping(PRODUCT_ROOT_PATH)
    public String openCreateProductForm(Model model) {
        model.addAttribute("productDto", ProductDto.builder().build());
        model.addAttribute("productStatusList", productStatusService.getProductStatuses());
        model.addAttribute("productCategoryList", productCategoryService.getProductCategories());
        model.addAttribute("operationStationsList", operationStationService.getOperationStations());

        return "products/product";
    }

    @PostMapping(PRODUCT_ROOT_PATH)
    public String createProduct(Model model, @Valid ProductDto productDto, BindingResult errors,
                                RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("productStatusList", productStatusService.getProductStatuses());
            model.addAttribute("productCategoryList", productCategoryService.getProductCategories());
            model.addAttribute("operationStationsList", operationStationService.getOperationStations());
            return "products/product";
        }
        productService.addProduct(productDto);
        redirectAttributes.addAttribute("message", "product.create.success");
        return "redirect:" + PUBLIC_PRODUCTS_ROOT_PATH;
    }

    @GetMapping(PUBLIC_PRODUCTS_ROOT_PATH)
    public String getProducts(
            Model model, @PageableDefault(size = 6, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("productListPaginated", productService.getProductsPaginated(pageable));
        model.addAttribute("orderedProducts", productionOrderService.getOrderedProductsUUIDs());
        return "products/products";
    }

    @GetMapping("/product/{productId}/update")
    public String getUpdateProduct(Model model, @PathVariable("productId") UUID id) {
        model.addAttribute("productDto", productService.getProductByUUID(id));
        model.addAttribute("productStatusList", productStatusService.getProductStatuses());
        model.addAttribute("productCategoryList", productCategoryService.getProductCategories());
        model.addAttribute("operationStationsList", operationStationService.getOperationStations());

        return "products/product";
    }

    @PostMapping("/product/{productId}/update")
    public String getUpdateProduct(Model model, @Valid ProductDto productDto, BindingResult errors) {
        if (errors.hasErrors()) {
            model.addAttribute("productStatusList", productStatusService.getProductStatuses());
            model.addAttribute("productCategoryList", productCategoryService.getProductCategories());
            model.addAttribute("operationStationsList", operationStationService.getOperationStations());

            return "products/product";
        }
        productService.updateProduct(productDto);
        return "redirect:" + PUBLIC_PRODUCTS_ROOT_PATH;
    }

    @PostMapping(PRODUCT_DELETE_PATH)
    public String deleteProduct(@RequestParam UUID productId) {
        productService.deleteProduct(productId);
        return "redirect:" + PUBLIC_PRODUCTS_ROOT_PATH;
    }


}
