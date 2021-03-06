package eu.codeacademy.vteshop.operation.station.repository;

import eu.codeacademy.vteshop.operation.station.entity.OperationStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStationRepository extends JpaRepository<OperationStation, Long> {
    Optional<OperationStation> findOperationStationByName(String operationStationName);

}
