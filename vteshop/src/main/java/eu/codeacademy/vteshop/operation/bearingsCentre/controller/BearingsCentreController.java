package eu.codeacademy.vteshop.operation.bearingsCentre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BearingsCentreController {


    @GetMapping("/bearings_centre")
    public String bearingCentre(){
        return "production/bearings";
    }
}
