package de.earley.wagenstandsanzeiger;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WagenstandsanzeigerServiceTest {

    @Test
    void loadWagenreihungsplanData() {
    }

    @Test
    void load() {
    }

    /*@Test
    void findSections() {
        List<Character> sections = Arrays.asList('D','C');
        List<Waggon> waggonList = Arrays.asList(new Waggon(sections, 6));
        List<Integer> trainNumberList = Arrays.asList(1515);
        List<Train> trainList = Arrays.asList(new Train(trainNumberList, waggonList));
        List<Track> trackList = Arrays.asList(new Track(trainList));
        Station station = new Station("AA",trackList);
        WagenstandsanzeigerService wagenstandsanzeigerService =new WagenstandsanzeigerService();
        List<Character> foundSections = wagenstandsanzeigerService.findSections("AA", 1515, 6);
        assertEquals(foundSections, Arrays.asList('D','C'));
    } */
}