package eu.codeacademy.vteshop.api.orders.production.service;

import eu.codeacademy.vteshop.api.orders.production.mapper.ProductionOrderStatusMapper;
import eu.codeacademy.vteshop.api.orders.production.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.jpa.orders.production.repository.ProductionOrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductionOrderStatusService {
    private final ProductionOrderStatusRepository statusRepository;
    private final ProductionOrderStatusMapper statusMapper;

    public List<ProductionOrderStatusDto> getOrderStatuses(){
        return statusRepository.findAll().stream()
                .map(statusMapper::mapTo)
                .collect(Collectors.toList());
    }

}
