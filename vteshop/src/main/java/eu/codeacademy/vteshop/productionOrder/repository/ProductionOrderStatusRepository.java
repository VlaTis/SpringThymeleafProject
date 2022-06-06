package eu.codeacademy.vteshop.productionOrder.repository;

import eu.codeacademy.vteshop.product.entity.ProductStatus;
import eu.codeacademy.vteshop.productionOrder.entity.ProductionOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductionOrderStatusRepository extends JpaRepository<ProductionOrderStatus, Long> {
    Optional<ProductionOrderStatus> findProductionOrderStatusByName(String productionOrderStatusName);
}
