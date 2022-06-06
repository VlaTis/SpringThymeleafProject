package eu.codeacademy.vteshop.operation.operationStation.service;

import eu.codeacademy.vteshop.operation.operationStation.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.operation.operationStation.entity.OperationStationStatus;
import eu.codeacademy.vteshop.operation.operationStation.mapper.OperationStationStatusMapper;
import eu.codeacademy.vteshop.operation.operationStation.repository.OperationStationStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationStationStatusService {

    private final OperationStationStatusMapper operationStationStatusMapper;
    private final OperationStationStatusRepository operationStationStatusRepository;

    @Transactional
    public void addOperationStationStatus(OperationStationStatusDto operationStationStatusDto){
        operationStationStatusRepository.save(OperationStationStatus.builder()
                .name(operationStationStatusDto.getName())
                .build());
    }

    public List<OperationStationStatusDto> getStationStatuses(){
        return operationStationStatusRepository.findAll().stream()
                .map(operationStationStatusMapper::mapTo)
                .collect(Collectors.toList());
    }

}
