package eu.codeacademy.vteshop.operation.operationStation.mapper;

import eu.codeacademy.vteshop.operation.operationStation.entity.OperationStation;
import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationDto;
import org.springframework.stereotype.Component;

@Component
public class OperationStationMapper {
    public OperationStationDto mapTo(OperationStation operationStation){
        return OperationStationDto.builder()
                .name(operationStation.getName())
                .build();
    }
}
