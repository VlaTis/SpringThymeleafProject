package eu.codeacademy.vteshop.api.controller.operation.station;


import eu.codeacademy.vteshop.common.station.dto.OperationStationDto;
import eu.codeacademy.vteshop.common.station.service.OperationStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation_stations")
@Api(tags = "Operation Station Controller")

public class OperationStationApiController {

    private final OperationStationService stationService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Get all operation stations",
            notes = "Get all operation stations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "not logged in"),
            @ApiResponse(code = 403, message = "not authorized")
    })
    public List<OperationStationDto> getStations() {
        return stationService.getOperationStations();
    }


//

    @GetMapping(
            path = "/{station}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Get station By name",
            notes = "Get station By name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "not logged in"),
            @ApiResponse(code = 403, message = "not authorized")
    })
    public OperationStationDto getStationByName(@PathVariable("station") String stationName) {
        return stationService.getOperationStationByName(stationName);
    }

    @PostMapping
    @ApiOperation(value = "Create operation station", httpMethod = "POST")
    public ResponseEntity<Void> createStation(@Valid @RequestBody OperationStationDto stationDto) {
        stationService.addOperationStation(stationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(value = "Update Station Status", httpMethod = "PUT")
    public ResponseEntity<Void> updateStationStatus(@Valid @RequestBody OperationStationDto stationDto) {
        if (stationService.updateOperationStationStatus(stationDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }

}
