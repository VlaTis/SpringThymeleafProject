package eu.codeacademy.vteshop.jpa.operation.station.repository;

import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationStationStatusRepository extends JpaRepository<OperationStationStatus, Long> {
    Optional<OperationStationStatus> findOperationStationStatusByName (String operationStationStatusName);

}