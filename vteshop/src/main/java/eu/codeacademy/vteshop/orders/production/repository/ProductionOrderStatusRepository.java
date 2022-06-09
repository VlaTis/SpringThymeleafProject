package eu.codeacademy.vteshop.orders.production.repository;

import eu.codeacademy.vteshop.orders.production.entity.ProductionOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductionOrderStatusRepository extends JpaRepository<ProductionOrderStatus, Long> {
    Optional<ProductionOrderStatus> findProductionOrderStatusByName(String productionOrderStatusName);
}
