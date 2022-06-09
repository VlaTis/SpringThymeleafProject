package eu.codeacademy.vteshop.operation.station.mapper;

import eu.codeacademy.vteshop.operation.station.entity.OperationStation;
import eu.codeacademy.vteshop.operation.station.dto.OperationStationDto;
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
