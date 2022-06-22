package eu.codeacademy.vteshop.common.station.service;

import eu.codeacademy.vteshop.common.station.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStationStatus;
import eu.codeacademy.vteshop.jpa.operation.station.repository.OperationStationStatusRepository;
import eu.codeacademy.vteshop.common.station.mapper.OperationStationStatusMapper;
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