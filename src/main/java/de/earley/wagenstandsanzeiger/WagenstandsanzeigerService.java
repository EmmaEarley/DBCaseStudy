package de.earley.wagenstandsanzeiger;

import java.util.List;

public interface WagenstandsanzeigerService {
    List<String> findSections(String ril100, String trainNumber, String waggonNumber);
}
