package eu.codeacademy.vteshop.api.product.service;


import eu.codeacademy.vteshop.api.product.dto.ProductCategoryDto;
import eu.codeacademy.vteshop.jpa.product.entity.ProductCategory;
import eu.codeacademy.vteshop.jpa.product.repository.ProductCategoryRepository;
import eu.codeacademy.vteshop.api.product.mapper.ProductCategoryMapper;
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

    public ProductCategoryDto getProductCategoryByName(String categoryName){
        return productCategoryMapper.mapTo(productCategoryRepository.findProductCategoryByName(categoryName).get());
    }

    public List<ProductCategoryDto> getProductCategories(){
        return productCategoryRepository.findAll().stream()
                .map(productCategoryMapper::mapTo)
                .collect(Collectors.toList());
    }


}
