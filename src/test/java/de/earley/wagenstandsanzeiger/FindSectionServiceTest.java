package de.earley.wagenstandsanzeiger;

import de.earley.wagenstandsanzeiger.domain.Station;
import de.earley.wagenstandsanzeiger.domain.Track;
import de.earley.wagenstandsanzeiger.domain.Train;
import de.earley.wagenstandsanzeiger.domain.Waggon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindSectionServiceTest {

    //tests if findSections works correctly
    @Test
    void findSections() {
        List<String> sections = List.of("D","C");
        List<Waggon> waggonList = List.of(new Waggon(sections, "32"));
        List<String> trainNumberList = List.of("1515");
        List<Train> trainList = List.of(new Train(trainNumberList, waggonList));
        List<Track> trackList = List.of(new Track(trainList));
        Station station = new Station("AA",trackList);
        FindSectionService service = new FindSectionService();
        List<String> foundSections = service.findSections(station, "1515", "32");
        assertEquals(foundSections, List.of("D","C"));
    }
}