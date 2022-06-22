package eu.codeacademy.vteshop.common.station.mapper;

import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStation;
import eu.codeacademy.vteshop.common.station.dto.OperationStationDto;
import org.springframework.stereotype.Component;

@Component
public class OperationStationMapper {
    public OperationStationDto mapTo(OperationStation operationStation){
        return OperationStationDto.builder()
                .name(operationStation.getName())
                .status_name(operationStation.getOperationStationStatus().getName())
                .build();
    }
}
