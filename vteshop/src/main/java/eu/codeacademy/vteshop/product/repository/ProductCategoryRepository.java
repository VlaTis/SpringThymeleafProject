package eu.codeacademy.vteshop.product.repository;

import eu.codeacademy.vteshop.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
