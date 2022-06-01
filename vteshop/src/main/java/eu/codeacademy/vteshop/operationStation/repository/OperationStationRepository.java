package eu.codeacademy.vteshop.operationStation.repository;

import eu.codeacademy.vteshop.operationStation.entity.OperationStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStationRepository extends JpaRepository<OperationStation, Long> {
    Optional<OperationStation> findOperationStationByName(String operationStationName);

}
