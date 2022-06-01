package eu.codeacademy.vteshop.product.service;

import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.entity.Product;
import eu.codeacademy.vteshop.product.repository.ProductCategoryRepository;
import eu.codeacademy.vteshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

//    @Transactional
//    public void addProduct(ProductDto productDto){
//        productRepository.save(Product.builder()
//                .productId(UUID.randomUUID())
//                .name(productDto.getName())
//                .quantityInStock(productDto.getQuantity())
//                .description(productDto.getDescription())
//                .build())
//    }
}
