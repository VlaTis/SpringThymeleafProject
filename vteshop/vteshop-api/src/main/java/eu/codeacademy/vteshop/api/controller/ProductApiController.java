package eu.codeacademy.vteshop.api.controller;


import eu.codeacademy.vteshop.common.product.dto.ProductDto;
import eu.codeacademy.vteshop.common.product.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Api
public class ProductApiController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }
}
