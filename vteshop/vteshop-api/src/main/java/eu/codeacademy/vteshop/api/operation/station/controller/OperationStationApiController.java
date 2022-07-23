package eu.codeacademy.vteshop.api.operation.station.controller;


import eu.codeacademy.vteshop.api.operation.station.dto.OperationStationDto;
import eu.codeacademy.vteshop.api.operation.station.dto.OperationStationStatusDto;
import eu.codeacademy.vteshop.api.operation.station.service.OperationStationService;
import eu.codeacademy.vteshop.api.operation.station.service.OperationStationStatusService;
import eu.codeacademy.vteshop.common.OpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation_stations")
@Api(tags = "Operation Station Controller")
@OpenApi

public class OperationStationApiController {

    private final OperationStationService stationService;
    private final OperationStationStatusService statusService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Get all operation stations",
            notes = "Get all operation stations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "not logged in"),
            @ApiResponse(code = 403, message = "not authorized")
    })
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public List<OperationStationDto> getStations() {
        return stationService.getOperationStations();
    }


    @GetMapping(
            path = "/statuses",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Get available statuses",
            notes = "Get available statuses")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public List<OperationStationStatusDto> getStationStatuses() {
        return statusService.getStationStatuses();
    }

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
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public OperationStationDto getStationByName(@PathVariable("station") String stationName) {
        return stationService.getOperationStationByName(stationName);
    }

    @PostMapping
    @ApiOperation(value = "Create operation station", httpMethod = "POST")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createStation(@Valid @RequestBody OperationStationDto stationDto) {
        stationService.addOperationStation(stationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(value = "Update Station Status", httpMethod = "PUT")
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    public ResponseEntity<Void> updateStationStatus(@Valid @RequestBody OperationStationDto stationDto) {
        if (stationService.updateOperationStationStatus(stationDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }

}
