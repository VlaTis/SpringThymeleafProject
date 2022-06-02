package eu.codeacademy.vteshop.product.controller;

import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.service.ProductService;
import eu.codeacademy.vteshop.product.service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductStatusService productStatusService;

    @GetMapping("/product")
    public String openCreateProductForm(Model model) {
        model.addAttribute("productDto", ProductDto.builder().build());
        model.addAttribute("productStatusList", productStatusService.getProductStatuses());
        return "products/product";
    }

    @PostMapping("/product")
    public String createProduct(ProductDto productDto){
        productService.addProduct(productDto);
        return "redirect:" + "products/product" ;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("productList", productService.getProducts());
        return "products/products";
    }


}
