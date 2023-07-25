package de.earley.wagenstandsanzeiger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Waggon(List<String> sections,@JsonProperty("number") String waggonNumber) {
    //the waggonNumber is a String so as to accommodate waggons with alphabetical "numbers"
    // (is able to find sections even though they are further wrapped in identifiers
}
