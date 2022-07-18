package eu.codeacademy.vteshop.api.controller.operation.station;



import eu.codeacademy.vteshop.common.station.dto.OperationStationDto;
import eu.codeacademy.vteshop.common.station.service.OperationStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
            value = "Get all product",
            notes = "Get all product from db, and any other information could be here")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sekmingai grazina produktus"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant produktu sarasa"),
            @ApiResponse(code = 403, message = "Neturite reikalingu teisiu gauti produktu sarasa")
    })
    public List<OperationStationDto> getStations(){
        return stationService.getOperationStations();
    }
}
