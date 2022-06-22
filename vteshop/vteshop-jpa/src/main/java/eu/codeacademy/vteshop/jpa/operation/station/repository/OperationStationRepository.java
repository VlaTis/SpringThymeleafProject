package eu.codeacademy.vteshop.jpa.operation.station.repository;

import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStationRepository extends JpaRepository<OperationStation, Long> {
    Optional<OperationStation> findOperationStationByName(String operationStationName);

}
