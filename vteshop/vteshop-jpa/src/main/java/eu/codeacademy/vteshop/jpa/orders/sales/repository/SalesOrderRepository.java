package eu.codeacademy.vteshop.jpa.orders.sales.repository;


import eu.codeacademy.vteshop.jpa.orders.sales.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    Optional<SalesOrder> findSalesOrderByName(String salesOrderName);
}
