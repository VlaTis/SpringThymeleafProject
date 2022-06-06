package eu.codeacademy.vteshop.fragments;


import eu.codeacademy.vteshop.operationStation.service.OperationStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FragmentsController {
    private final OperationStationService operationStationService;

    @GetMapping
    public String testing(Model model){
        model.addAttribute("operationStationList", operationStationService.getOperationStations());
        return "/fragments/layout";
    }
}
