package eu.codeacademy.vteshop.operationStation.service;

import eu.codeacademy.vteshop.operationStation.dto.OperationStationDto;
import eu.codeacademy.vteshop.operationStation.entity.OperationStation;
import eu.codeacademy.vteshop.operationStation.mapper.OperationStationMapper;
import eu.codeacademy.vteshop.operationStation.repository.OperationStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationStationService {
    private final OperationStationRepository operationStationRepository;
    private final OperationStationMapper operationStationMapper;

    @Transactional
    public void addOperationStation(OperationStationDto operationStationDto) {
        operationStationRepository.save(OperationStation.builder()
                .name(operationStationDto.getName())
                .build());
    }

    public List<OperationStationDto> getOperationStations(){
        return operationStationRepository.findAll().stream()
                .map(operationStationMapper::mapTo)
                .collect(Collectors.toList());
    }
}
