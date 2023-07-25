package de.earley.wagenstandsanzeiger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.earley.wagenstandsanzeiger.domain.Station;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.RequestEntity.put;

@Service
@Qualifier("Preload")
public class PreloadWagenstandsanzeigerService implements WagenstandsanzeigerService {
    private Map<String, Station> stations;

    @Autowired
    ResourcePatternResolver resourceLoader;
    @Autowired
    FindSectionService sectionService;

    public Resource[] loadWagenreihungsplanData() throws IOException {
        return resourceLoader.getResources(
                "classpath:/Wagenreihungsplan_RawData_201712112/*.xml");
    }

    /*void load() reads all files in the resources/Wagenreihungsplan_RawData_201712112 folder and
     transforms them into a List of Stations
    */
    @PostConstruct
    public void load() throws IOException {
        //load the resources
        Resource[] resources = loadWagenreihungsplanData();
        //open the folder containing the xml files
        //File folder = new File("src/main/resources/Wagenreihungsplan_RawData_201712112");
        //save files in a list
        //File[] fileList = folder.listFiles();
        //create XML mapper which ignores unknown properties
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //create a Map and fill it with the station from each file with its shortcode as key
        Map<String, Station> stationMap = new HashMap<>();
        for (Resource resource : resources) {
            System.out.println(resource.getFilename());
            Station station = xmlMapper.readValue(resource.getInputStream(), Station.class);
            stationMap.put(station.shortcode(), station);
        }
        //save all stations in the private stations variable
        this.stations = stationMap;
    }

    @Override
    public List<String> findSections(String ril100, String trainNumber, String waggonNumber) {
        Station station = findStation(ril100);
        return sectionService.findSections(station, trainNumber, waggonNumber);
    }

    //return the desired Station element (from the correct xml file) or throw exception if ril100 can't be found/station is null
    private Station findStation(String ril100) {
        Station station = this.stations.getOrDefault(ril100, null);
        if (station != null) {
            return station;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unbekannte ril100.");
    }

}
