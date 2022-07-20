package eu.codeacademy.vteshop.api.controller.product;


import eu.codeacademy.vteshop.api.dto.ProductResponse;
import eu.codeacademy.vteshop.common.product.dto.ProductDto;
import eu.codeacademy.vteshop.common.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Api(tags = "Product Controller")
public class ProductApiController {

    private final ProductService productService;


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
            path = "/{uuid}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get one product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sekmingai grazina produkta"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant produkta"),
            @ApiResponse(code = 403, message = "Neturite reikalingu teisiu gauti produkta")
    })
    public ProductResponse getProducts(@PathVariable("uuid") UUID uuid) {
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


}




