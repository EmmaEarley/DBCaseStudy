package de.earley.wagenstandsanzeiger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WagenstandsanzeigerController {
    @Autowired
    @Qualifier("OnDemand")
    WagenstandsanzeigerService service;

    /**
     * @param ril100 a shortcode for a specific station
     * @param trainNumber
     * @param number
     * @return the sections corresponding to the given train and waggon
     */
    @GetMapping(value = "/station/{ril100}/train/{trainNumber}/waggon/{number}", produces = "application/json")
    public SectionResponse getSections(@PathVariable String ril100, @PathVariable String trainNumber, @PathVariable String number){
        List<String> sections = service.findSections(ril100, trainNumber, number);
        return new SectionResponse(sections);
    }

}
