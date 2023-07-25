package de.earley.wagenstandsanzeiger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Station(String shortcode, @JsonProperty("tracks") List<Track> trackList) {

}
