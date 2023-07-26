package de.earley.wagenstandsanzeiger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.earley.wagenstandsanzeiger.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.List;

//Service class that provides implementation on how to load the data and find the desired station
//Loads only the neccessary xml file
@Service
@Qualifier("OnDemand")
public class OnDemandWagenstandsanzeigerService implements WagenstandsanzeigerService {

    @Autowired
    FindSectionService sectionService;

    //return a list of the sections
    @Override
    public List<String> findSections(String ril100, String trainNumber, String waggonNumber) {
        Station station = null;
        try {station = findStation(ril100);}
        catch (Exception e) {throw new RuntimeException(e);}
        return sectionService.findSections(station, trainNumber, waggonNumber);
    }

    //return the desired Station element (from the correct xml file)
    private Station findStation(String ril100) throws IOException {
        //create XML mapper which ignores unknown properties
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //open the folder containing the xml files
        File folder = new File("src/main/resources/Wagenreihungsplan_RawData_201712112");
        //find correct file
        for (File file : folder.listFiles()) {
            if (file.getName().startsWith(ril100+"_")) {
                return xmlMapper.readValue(file, Station.class);
            }
        }
        //throw exception if no file with the given ril100 was found
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unbekannte ril100.");
    }

}
