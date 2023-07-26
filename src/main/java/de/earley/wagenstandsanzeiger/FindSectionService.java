package de.earley.wagenstandsanzeiger;

import de.earley.wagenstandsanzeiger.domain.Station;
import de.earley.wagenstandsanzeiger.domain.Track;
import de.earley.wagenstandsanzeiger.domain.Train;
import de.earley.wagenstandsanzeiger.domain.Waggon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

//Service class to determine the desired section, given a Station, trainNumber and waggonNumber
@Service
public class FindSectionService {
    public List<String> findSections(Station station, String trainNumber, String waggonNumber){
        List<Train> trains = findTrain(station, trainNumber);
        Waggon waggon = findWaggon(trains, waggonNumber);
        return waggon.sections();
    }

    //determine train(s) at that station with a specific train number
    //TODO: Was soll ich machen, wenn mehrere Züge die gleiche Nummer haben?
    private List<Train> findTrain(Station station, String trainNumber) {
        List<Train> trains = new ArrayList<>();
        //check all trains for desired number
        for (Track track : station.trackList()){
            for (Train train : track.trainList()) {
                if (train.trainNumberList().contains(trainNumber)) {
                    trains.add(train);
                }
            }
        }
        if (trains.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zug wurde nicht gefunden.");
        } else {
            //return all trains with that number
            return trains;
        }
    }

    //find correct waggon from a given train and number
    //TODO: finds only first occurrence of the waggon (may be the wrong one though...)
    private Waggon findWaggon(List<Train> trains, String waggonNumber) {
        //find first waggon with given number
        for (Train train : trains) {
            for (Waggon waggon : train.waggonList()) {
                if (waggonNumber.equals(waggon.waggonNumber())) {
                    return waggon;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Waggon wurde nicht gefunden.");
    }

}

