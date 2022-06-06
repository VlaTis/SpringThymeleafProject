package eu.codeacademy.vteshop.product.service;

import eu.codeacademy.vteshop.operationStation.entity.OperationStation;
import eu.codeacademy.vteshop.operationStation.repository.OperationStationRepository;
import eu.codeacademy.vteshop.product.dto.ProductDto;
import eu.codeacademy.vteshop.product.entity.Product;
import eu.codeacademy.vteshop.product.entity.ProductCategory;
import eu.codeacademy.vteshop.product.entity.ProductStatus;
import eu.codeacademy.vteshop.product.exeption.ProductNotFoundException;
import eu.codeacademy.vteshop.product.mapper.ProductMapper;
import eu.codeacademy.vteshop.product.repository.ProductCategoryRepository;
import eu.codeacademy.vteshop.product.repository.ProductRepository;
import eu.codeacademy.vteshop.product.repository.ProductStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductStatusRepository productStatusRepository;
    private final OperationStationRepository operationStationRepository;

    @Transactional
    public void addProduct(ProductDto productDto) {
        productRepository.save(Product.builder()
                .productId(UUID.randomUUID())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantityInStock(productDto.getQuantity())
                .description(productDto.getDescription())
                .productCategory(productCategoryRepository.findProductCategoryByName(productDto.getProductCategoryName()).get())
                .operationStation(operationStationRepository.findOperationStationByName(productDto.getOperationStationName()).get())
                .productStatus(productStatusRepository.findProductStatusByName(productDto.getProductStatusName()).get())
                .build());
    }

    public Page<ProductDto> getProductsPaginated(Pageable pageable){
        return productRepository.findAll(pageable)
                .map(productMapper::mapTo);
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());

    }

    public ProductDto getProductByUUID(UUID id) {
        return productRepository.findByProductId(id)
                .map(productMapper::mapTo)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


    @Transactional
    public void updateProduct(ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findByProductId(productDto.getProductId());

        if (productOptional.isPresent()) {
            Product product = productOptional.get().toBuilder()
                    .name(productDto.getName())
                    .quantityInStock(productDto.getQuantity())
                    .price(productDto.getPrice())
                    .description(productDto.getDescription())
                    .productCategory(productCategoryRepository.findProductCategoryByName(productDto.getProductCategoryName()).get())
                    .productStatus(productStatusRepository.findProductStatusByName(productDto.getProductStatusName()).get())
                    .operationStation(operationStationRepository.findOperationStationByName(productDto.getOperationStationName()).get())
                    .build();

            productRepository.save(product);
        }
    }


    @Transactional
    public void deleteProduct(UUID uuid) {
        Optional<Product> product = productRepository.findByProductId(uuid);
        product.ifPresent(value -> productRepository.deleteById(value.getId()));
    }

}
