package eu.codeacademy.vteshop.api.product.service;

import eu.codeacademy.vteshop.api.product.dto.ProductStatusDto;
import eu.codeacademy.vteshop.api.product.mapper.ProductStatusMapper;
import eu.codeacademy.vteshop.jpa.product.entity.ProductStatus;
import eu.codeacademy.vteshop.jpa.product.repository.ProductStatusRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductStatusService {

    private final ProductStatusRepository productStatusRepository;
    private final ProductStatusMapper productStatusMapper;

    @Transactional
    public void addProductStatus(ProductStatusDto productStatusDto){
        productStatusRepository.save(ProductStatus.builder()
                .name(productStatusDto.getName())
                .build());
    }

    public List<ProductStatusDto> getProductStatuses(){
        return productStatusRepository.findAll().stream()
                .map(productStatusMapper::mapTo)
                .collect(Collectors.toList());
    }

}
