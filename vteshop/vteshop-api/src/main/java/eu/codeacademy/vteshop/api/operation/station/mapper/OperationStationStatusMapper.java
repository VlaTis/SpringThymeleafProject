package eu.codeacademy.vteshop.api.operation.station.mapper;

import eu.codeacademy.vteshop.api.operation.station.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStationStatus;
import org.springframework.stereotype.Component;

@Component
public class OperationStationStatusMapper {


    public OperationStationStatusDto mapTo(OperationStationStatus operationStationStatus){
        return OperationStationStatusDto.builder()
                .name(operationStationStatus.getName())
                .build();
    }
}
