package eu.codeacademy.vteshop.operation.operationStation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class OperationStationDto {
    private String name;
    private String status_name;
}
