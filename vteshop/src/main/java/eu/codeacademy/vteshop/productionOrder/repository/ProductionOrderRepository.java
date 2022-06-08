package eu.codeacademy.vteshop.productionOrder.repository;

import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    Optional<ProductionOrder> findProductionOrderByName(String productionOrderName);
}
