package eu.codeacademy.vteshop.operation.operationStation.entity;



import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OperationStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "operation_station_status")
    private OperationStationStatus operationStationStatus;


}
