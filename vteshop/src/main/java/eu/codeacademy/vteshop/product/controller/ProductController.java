package eu.codeacademy.vteshop.product.controller;

import eu.codeacademy.vteshop.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.operationStation.service.OperationStationService;
import eu.codeacademy.vteshop.product.dto.ProductCategoryDto;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.dto.ProductStatusDto;
import eu.codeacademy.vteshop.product.service.ProductCategoryService;
import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.product.service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductStatusService productStatusService;
    private final ProductCategoryService productCategoryService;
    private final OperationStationService operationStationService;

    @GetMapping("/product")
    public String openCreateProductForm(Model model) {
        model.addAttribute("productDto", ProductDto.builder().build());
        model.addAttribute("productStatusList", productStatusService.getProductStatuses());
        model.addAttribute("productCategoryList", productCategoryService.getProductCategories());
        model.addAttribute("operationStationsList", operationStationService.getOperationStations());
        return "products/product";
    }

    @PostMapping("/product")
    public String createProduct(ProductDto productDto){
        productService.addProduct(productDto);
        return "redirect:" + "products/product" ;
    }

    @GetMapping("/products")
    public String getProducts(
            Model model, @PageableDefault(size = 8, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("productList", productService.getProducts());
        return "products/products";
    }


}
