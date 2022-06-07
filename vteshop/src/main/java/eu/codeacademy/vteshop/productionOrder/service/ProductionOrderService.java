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
import java.util.Objects;
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
    public void updateProductionOrderStatus(String productionOrderDtoName, ProductionOrderStatusDto productionOrderStatusDto) {
        Optional<ProductionOrder> productionOrderOptional = productionOrderRepository.findProductionOrderByName(productionOrderDtoName);


        if (productionOrderOptional.isPresent()) {
            ProductionOrderDto productionOrderDto = findProductionOrderDtoByName(productionOrderDtoName);
            ProductionOrder productionOrder = productionOrderOptional.get().toBuilder()
                    .name(productionOrderDto.getName())
                    .quantity(productionOrderDto.getQuantity())
                    .product(productRepository.findByProductId(productionOrderDto.getProductUUID()).get())
                    .productionOrderStatus(productionOrderStatusRepository.findProductionOrderStatusByName(productionOrderStatusDto.getName()).get())
                    .build();

            productionOrderRepository.save(productionOrder);
        }
    }


    public List<ProductionOrderDto> getAllProductionOrders() {
        return productionOrderRepository.findAll().stream()
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());

    }

    private ProductionOrderDto findProductionOrderDtoByName(String productionOrderDtoName) {
        return productionOrderRepository.findProductionOrderByName(productionOrderDtoName).map(productionOrderMapper::mapTo).get();
    }

    public List<ProductionOrderDto> getFilteredProductionOrders(OperationStationDto operationStationDto, ProductionOrderStatusDto productionOrderStatusDto) {
        return productionOrderRepository.findAll().stream()
                .filter(po -> Objects.equals(po.getProductionOrderStatus().getName(), productionOrderStatusDto.getName())
                        && Objects.equals(po.getProduct().getOperationStation().getName(), operationStationDto.getName()))
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());


    }

    public List<ProductionOrderDto> getFilteredOrdersByStatus(ProductionOrderStatusDto productionOrderStatusDto) {
        return productionOrderRepository.findAll().stream()
                .filter(po -> Objects.equals(po.getProductionOrderStatus().getName(), productionOrderStatusDto.getName()))
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());
    }


}
