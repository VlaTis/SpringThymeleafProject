package eu.codeacademy.vteshop.jpa.orders.production.repository;

import eu.codeacademy.vteshop.jpa.orders.production.entity.ProductionOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductionOrderStatusRepository extends JpaRepository<ProductionOrderStatus, Long> {
    Optional<ProductionOrderStatus> findProductionOrderStatusByName(String productionOrderStatusName);
}
