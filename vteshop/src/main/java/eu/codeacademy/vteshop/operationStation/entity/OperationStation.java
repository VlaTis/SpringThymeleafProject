package eu.codeacademy.vteshop.operationStation.entity;


import eu.codeacademy.vteshop.product.entity.Product;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class OperationStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;


}
