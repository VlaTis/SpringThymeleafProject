package eu.codeacademy.vteshop.productionOrder.service;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrderStatus;
import eu.codeacademy.vteshop.productionOrder.mapper.ProductionOrderStatusMapper;
import eu.codeacademy.vteshop.productionOrder.repository.ProductionOrderStatusRepository;
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
