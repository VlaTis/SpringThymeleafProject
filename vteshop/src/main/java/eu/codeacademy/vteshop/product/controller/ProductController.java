package eu.codeacademy.vteshop.product.controller;

import eu.codeacademy.vteshop.operationStation.service.OperationStationService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

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
    public String createProduct(ProductDto productDto) {
        productService.addProduct(productDto);
        return "redirect:" + "/product";
    }

    @GetMapping("/products")
    public String getProducts(
            Model model, @PageableDefault(size = 8, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("productListPaginated", productService.getProductsPaginated(pageable));
        return "products/products";
    }

    @GetMapping("/products/{productId}/update")
    public String getUpdateProduct(Model model, @PathVariable("productId") UUID id) {
        model.addAttribute("productDto", productService.getProductByUUID(id));

        return "products/products";
    }

    @PostMapping("/products/{productId}/update")
    public String getUpdateProduct(ProductDto productDto) {
        productService.updateProduct(productDto);

        return "redirect:" + "/products";
    }
//
    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam UUID productId) {
        productService.deleteProduct(productId);

        return "redirect:" + "/products";
    }




}
