package eu.codeacademy.vteshop.jpa.orders.production.repository;

import eu.codeacademy.vteshop.jpa.orders.production.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    Optional<ProductionOrder> findProductionOrderByName(String productionOrderName);
    Optional<ProductionOrder> findProductionOrderByProductId(UUID productUUID);
}
