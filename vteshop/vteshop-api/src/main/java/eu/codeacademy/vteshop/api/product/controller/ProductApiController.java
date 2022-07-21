package eu.codeacademy.vteshop.api.product.controller;


import eu.codeacademy.vteshop.api.product.dto.ProductCategoryDto;
import eu.codeacademy.vteshop.api.product.dto.ProductResponse;
import eu.codeacademy.vteshop.api.product.dto.ProductDto;
import eu.codeacademy.vteshop.api.product.dto.ProductStatusDto;
import eu.codeacademy.vteshop.api.product.service.ProductCategoryService;
import eu.codeacademy.vteshop.api.product.service.ProductService;
import eu.codeacademy.vteshop.api.product.service.ProductStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Api(tags = "Product Controller")
public class ProductApiController {

    private final ProductService productService;
    private final ProductStatusService statusService;

    private final ProductCategoryService categoryService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
            value = "Get all product",
            notes = "Get all product from db, and any other information could be here")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sekmingai grazina produktus"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant produktu sarasa"),
            @ApiResponse(code = 403, message = "Neturite reikalingu teisiu gauti produktu sarasa")
    })
    public ProductResponse getProducts() {
        return ProductResponse.builder()
                .products(productService.getProducts())
                .build();
    }


    @GetMapping(
            path = "/statuses",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Get status list",
            notes = "Available status")
    public List<ProductStatusDto> getProductStatuses() {
        return statusService.getProductStatuses();
    }


    @GetMapping("/page")
    public Page<ProductDto> getProductsPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return productService.getProductsPaginated(PageRequest.of(page, size));
    }

    @GetMapping(
            path = "/{uuid}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get one product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sekmingai grazina produkta"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant produkta"),
            @ApiResponse(code = 403, message = "Neturite reikalingu teisiu gauti produkta")
    })
    public ProductResponse getProductByUUID(@PathVariable("uuid") UUID uuid) {
        return ProductResponse.builder()
                .products(List.of(productService.getProductByUUID(uuid)))
                .build();
    }


    @DeleteMapping(path = "/{uuid}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 401, message = "Need to log in"),
            @ApiResponse(code = 403, message = "Unauthorised"),
//            @ApiResponse(code = 500, message = "Product can not be deleted: it belongs to production order") //geriau negrazinti 500
    })
    public void deleteProduct(@PathVariable("uuid") UUID productId) {
        productService.deleteProduct(productId);
    }


    @PutMapping
    @ApiOperation(value = "Update product", httpMethod = "PUT")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductDto productDto) {
        if (productService.updateProduct(productDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ApiOperation(value = "Create product", httpMethod = "POST")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductDto productDto) {
        productService.addProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/categories")
    @ApiOperation(value = "Get product categories list")
    public List<ProductCategoryDto> getProductCategories() {
        return categoryService.getProductCategories();
    }

    @PostMapping("/categories")
    @ApiOperation(value = "Create new category", httpMethod = "POST")
    public ResponseEntity<Void> createCategory(@Valid @RequestBody ProductCategoryDto categoryDto) {
        categoryService.addProductCategory(categoryDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}




