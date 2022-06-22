package eu.codeacademy.vteshop.common.station.mapper;

import eu.codeacademy.vteshop.common.station.dto.OperationStationStatusDto;
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
