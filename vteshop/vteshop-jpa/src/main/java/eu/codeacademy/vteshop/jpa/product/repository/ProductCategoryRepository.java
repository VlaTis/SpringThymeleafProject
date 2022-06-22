package eu.codeacademy.vteshop.jpa.product.repository;

import eu.codeacademy.vteshop.jpa.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findProductCategoryByName(String productCategoryName);
}
