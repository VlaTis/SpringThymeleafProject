package eu.codeacademy.vteshop.product.repository;


import eu.codeacademy.vteshop.product.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
    Optional<ProductStatus> findProductStatusByName(String productStatusName);
}
