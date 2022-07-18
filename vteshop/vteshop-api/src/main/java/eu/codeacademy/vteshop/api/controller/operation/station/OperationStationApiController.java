package eu.codeacademy.vteshop.api.controller.operation.station;



import eu.codeacademy.vteshop.common.station.dto.OperationStationDto;
import eu.codeacademy.vteshop.common.station.service.OperationStationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation_stations")
@Api(tags = "Operation Station Controller")

public class OperationStationApiController {

    private final OperationStationService stationService;

    @GetMapping()
    public List<OperationStationDto> getStations(){
        return stationService.getOperationStations();
    }
}
