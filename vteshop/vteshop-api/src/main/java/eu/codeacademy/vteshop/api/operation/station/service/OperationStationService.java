package eu.codeacademy.vteshop.api.operation.station.service;

import eu.codeacademy.vteshop.jpa.operation.station.entity.OperationStation;
import eu.codeacademy.vteshop.jpa.operation.station.repository.OperationStationRepository;
import eu.codeacademy.vteshop.jpa.operation.station.repository.OperationStationStatusRepository;
import eu.codeacademy.vteshop.api.operation.station.mapper.OperationStationMapper;
import eu.codeacademy.vteshop.api.operation.station.dto.OperationStationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationStationService {
    private final OperationStationRepository operationStationRepository;
    private final OperationStationMapper operationStationMapper;
    private final OperationStationStatusRepository statusRepository;

    @Transactional
    public void addOperationStation(OperationStationDto operationStationDto) {
        operationStationRepository.save(OperationStation.builder()
                .name(operationStationDto.getName())
                .operationStationStatus(statusRepository.findOperationStationStatusByName(operationStationDto.getStatus_name()).get())
                .build());
    }

    @Transactional
    public boolean updateOperationStationStatus(OperationStationDto operationStationDto){
        Optional<OperationStation> operationStationOptional = operationStationRepository.findOperationStationByName(operationStationDto.getName());

        if(operationStationOptional.isPresent()){
            OperationStation operationStation = operationStationOptional.get().toBuilder()
                    .name(operationStationOptional.get().getName())
                    .operationStationStatus(
                            statusRepository.findOperationStationStatusByName(operationStationDto.getStatus_name()).get())
                    .build();

            operationStationRepository.save(operationStation);
            return true;
        }

        return false;

    }

    public List<OperationStationDto> getOperationStations(){
        return operationStationRepository.findAll().stream()
                .map(operationStationMapper::mapTo)
                .collect(Collectors.toList());
    }

    public OperationStationDto getOperationStationByName(String opStation){
       return getOperationStations().stream()
               .filter(operationStationDto -> operationStationDto.getName().equalsIgnoreCase(opStation))
               .findAny()
               .get();
    }
}
