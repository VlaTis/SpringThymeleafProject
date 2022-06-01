package eu.codeacademy.vteshop.product.service;

import eu.codeacademy.vteshop.operationStation.repository.OperationStationRepository;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.entity.Product;
import eu.codeacademy.vteshop.product.entity.mapper.ProductMapper;
import eu.codeacademy.vteshop.product.repository.ProductCategoryRepository;
import eu.codeacademy.vteshop.product.repository.ProductRepository;
import eu.codeacademy.vteshop.product.repository.ProductStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductStatusRepository productStatusRepository;
    private final OperationStationRepository operationStationRepository;

    @Transactional
    public void addProduct(ProductDto productDto){
        productRepository.save(Product.builder()
                .productId(UUID.randomUUID())
                .name(productDto.getName())
                .quantityInStock(productDto.getQuantity())
                .description(productDto.getDescription())
                .productCategory(productCategoryRepository.findProductCategoryByName(productDto.getProductCategory().getName()).get())
                .operationStation(operationStationRepository.findOperationStationByName(productDto.getOperationStationDto().getName()).get())
                .productStatus(productStatusRepository.findProductStatusByName(productDto.getName()).get())
                .build());
    }

    public List<ProductDto> getProducts(){
        return productRepository.findAll().stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());

    }
}
