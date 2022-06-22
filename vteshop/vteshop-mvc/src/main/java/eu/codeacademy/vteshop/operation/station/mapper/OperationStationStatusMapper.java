package eu.codeacademy.vteshop.operation.station.mapper;

import eu.codeacademy.vteshop.operation.station.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.operation.station.entity.OperationStationStatus;
import org.springframework.stereotype.Component;

@Component
public class OperationStationStatusMapper {


    public OperationStationStatusDto mapTo(OperationStationStatus operationStationStatus){
        return OperationStationStatusDto.builder()
                .name(operationStationStatus.getName())
                .build();
    }
}
