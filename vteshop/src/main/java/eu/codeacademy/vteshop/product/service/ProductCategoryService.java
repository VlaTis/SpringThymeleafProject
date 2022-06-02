package eu.codeacademy.vteshop.product.service;

import eu.codeacademy.vteshop.product.dto.ProductCategoryDto;
import eu.codeacademy.vteshop.product.entity.ProductCategory;
import eu.codeacademy.vteshop.product.entity.mapper.ProductCategoryMapper;
import eu.codeacademy.vteshop.product.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    public void addProductCategory(ProductCategoryDto productCategoryDto){
        productCategoryRepository.save(ProductCategory.builder()
                .name(productCategoryDto.getName())
                .build());
    }

    public List<ProductCategoryDto> getProductCategories(){
        return productCategoryRepository.findAll().stream()
                .map(productCategoryMapper::mapTo)
                .collect(Collectors.toList());
    }
}
