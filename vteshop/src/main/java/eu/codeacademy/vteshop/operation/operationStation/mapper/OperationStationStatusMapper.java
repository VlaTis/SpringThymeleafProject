package eu.codeacademy.vteshop.operation.operationStation.mapper;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.operation.operationStation.entity.OperationStationStatus;
import org.springframework.stereotype.Component;

@Component
public class OperationStationStatusMapper {


    public OperationStationStatusDto mapTo(OperationStationStatus operationStationStatus){
        return OperationStationStatusDto.builder()
                .name(operationStationStatus.getName())
                .build();
    }
}
