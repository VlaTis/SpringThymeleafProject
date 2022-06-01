package eu.codeacademy.vteshop.operationStation.mapper;

import eu.codeacademy.vteshop.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.operationStation.entity.OperationStation;
import org.springframework.stereotype.Component;

@Component
public class OperationStationMapper {
    public OperationStationDto mapTo(OperationStation operationStation){
        return OperationStationDto.builder()
                .name(operationStation.getName())
                .build();
    }
}
