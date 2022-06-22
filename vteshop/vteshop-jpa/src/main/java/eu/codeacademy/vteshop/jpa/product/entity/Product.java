package eu.codeacademy.vteshop.jpa.product.entity;

import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStation;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID productId;

    private String name;

    private Integer quantityInStock;

    private BigDecimal price;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_status")
    private ProductStatus productStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "operation_station")
    private OperationStation operationStation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_category")
    private ProductCategory productCategory;

}
