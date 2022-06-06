package eu.codeacademy.vteshop.operation.operationStation.repository;

import eu.codeacademy.vteshop.operation.operationStation.entity.OperationStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStationRepository extends JpaRepository<OperationStation, Long> {
    Optional<OperationStation> findOperationStationByName(String operationStationName);

}
