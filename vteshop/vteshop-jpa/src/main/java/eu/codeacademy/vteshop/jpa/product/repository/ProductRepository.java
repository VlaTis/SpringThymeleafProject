package eu.codeacademy.vteshop.jpa.product.repository;

import eu.codeacademy.vteshop.product.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findProductsByNameIsLike(String productName, Pageable pageable);
    Optional<Product> findByProductId(UUID id);
}
