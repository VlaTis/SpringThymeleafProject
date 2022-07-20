package eu.codeacademy.vteshop.common.orders.production.service;

import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderDto;
import eu.codeacademy.vteshop.common.orders.production.dto.ProductionOrderStatusDto;
import eu.codeacademy.vteshop.common.orders.production.mapper.ProductionOrderMapper;
import eu.codeacademy.vteshop.common.station.dto.OperationStationDto;
import eu.codeacademy.vteshop.jpa.orders.production.entity.ProductionOrder;
import eu.codeacademy.vteshop.jpa.orders.production.repository.ProductionOrderRepository;
import eu.codeacademy.vteshop.jpa.orders.production.repository.ProductionOrderStatusRepository;
import eu.codeacademy.vteshop.jpa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
            ProductionOrderDto productionOrderDto = getProductionOrderDtoByName(productionOrderDtoName);
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

    public ProductionOrderDto getProductionOrderDtoByName(String productionOrderDtoName) {
        return productionOrderRepository.findProductionOrderByName(productionOrderDtoName).map(productionOrderMapper::mapTo).get();
    }

    public List<ProductionOrderDto> getFilteredProductionOrders(OperationStationDto operationStationDto, ProductionOrderStatusDto productionOrderStatusDto) {
        return productionOrderRepository.findAll().stream()
                .filter(po -> Objects.equals(po.getProductionOrderStatus().getName(), productionOrderStatusDto.getName())
                        && Objects.equals(po.getProduct().getOperationStation().getName(), operationStationDto.getName()))
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());


    }

    public List<ProductionOrderDto> getFilteredOrdersByStatusAndStation(ProductionOrderStatusDto productionOrderStatusDto, OperationStationDto operationStationDto) {
        return productionOrderRepository.findAll().stream()
                .filter(po -> Objects.equals(po.getProductionOrderStatus().getName(), productionOrderStatusDto.getName())
                        && Objects.equals(po.getProduct().getOperationStation().getName(), operationStationDto.getName()))
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());
    }


    public List<ProductionOrderDto> getFilteredOrdersByStationName(String stationName) {
        return productionOrderRepository.findAll().stream()
                .filter(po -> Objects.equals(po.getProduct()
                        .getOperationStation()
                        .getName(), stationName))
                .map(productionOrderMapper::mapTo)
                .collect(Collectors.toList());
    }


    public Page<ProductionOrderDto> getProductionOrdersPaginated(Pageable pageable) {
        return productionOrderRepository.findAll(pageable)
                .map(productionOrderMapper::mapTo);
    }


    @Transactional
    public boolean updateOrder(ProductionOrderDto orderDto) {
        Optional<ProductionOrder> orderOptional = productionOrderRepository.findProductionOrderByName(orderDto.getName());

        if (orderOptional.isPresent()) {
            ProductionOrder order = orderOptional.get().toBuilder()
                    .product(productRepository.findByProductId(orderDto.getProductUUID()).get())
                    .quantity(orderDto.getQuantity())
                    .name(orderDto.getName())
                    .build();

            productionOrderRepository.save(order);
            return true;
        }
        return false;
    }

    public Set<UUID> getOrderedProductsUUIDs() {
        return getAllProductionOrders().stream()
                .map(ProductionOrderDto::getProductUUID)
                .collect(Collectors.toSet());
    }

    public void deleteOrder(String orderName) {
        Optional<ProductionOrder> orderOptional = productionOrderRepository.findProductionOrderByName(orderName);
        orderOptional.ifPresent(order -> productionOrderRepository.delete(orderOptional.get()));
    }
}
