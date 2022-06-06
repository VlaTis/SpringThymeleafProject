package eu.codeacademy.vteshop.productionOrder.service;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.product.repository.ProductRepository;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.productionOrder.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrder;
import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrderStatus;
import eu.codeacademy.vteshop.productionOrder.mapper.ProductionOrderMapper;
import eu.codeacademy.vteshop.productionOrder.repository.ProductionOrderRepository;
import eu.codeacademy.vteshop.productionOrder.repository.ProductionOrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductionOrderService {

    private final ProductionOrderRepository productionOrderRepository;
    private final ProductionOrderStatusRepository productionOrderStatusRepository;
    private final ProductRepository productRepository;
    private final ProductionOrderMapper productionOrderMapper;

    @Transactional
    public void addProductionOrder(ProductionOrderDto productionOrderDto) {
        productionOrderRepository.save(ProductionOrder.builder()
                .name(productionOrderDto.getName())
                .quantity(productionOrderDto.getQuantity())
                .productionOrderStatus(productionOrderStatusRepository.findProductionOrderStatusByName(productionOrderDto.getOrder_status()).get())
                .product(productRepository.findByProductId(productionOrderDto.getProductUUID()).get())
                .build());

    }

    @Transactional
    public void updateProductionOrder(ProductionOrderDto productionOrderDto){
        Optional<ProductionOrder> productionOrderOptional = productionOrderRepository.findProductionOrderByName(productionOrderDto.getName());

        if(productionOrderOptional.isPresent()){
            ProductionOrder productionOrder = productionOrderOptional.get().toBuilder()
                    .name(productionOrderDto.getName())
                    .quantity(productionOrderDto.getQuantity())
                    .product(productRepository.findByProductId(productionOrderDto.getProductUUID()).get())
                    .productionOrderStatus(productionOrderStatusRepository.findProductionOrderStatusByName(productionOrderDto.getOrder_status()).get())
                    .build();
        }
    }

    public List<ProductionOrderDto> getAllProductionOrders(){
        return productionOrderRepository.findAll().stream()
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());

    }

    public List<ProductionOrderDto> getFilteredProductionOrders(OperationStationDto operationStationDto, ProductionOrderStatusDto productionOrderStatusDto){
        return productionOrderRepository.findAll().stream()
                .filter(po -> po.getProductionOrderStatus().getName() == productionOrderStatusDto.getName()
                        && po.getProduct().getOperationStation().getName() == operationStationDto.getName())
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());

    }


}
