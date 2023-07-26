package de.earley.wagenstandsanzeiger;

import java.util.List;

//class to provide the desired Json notation for the response
public record SectionResponse(List<String> sections) {
}
