package de.earley.wagenstandsanzeiger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Track(@JsonProperty("trains") List<Train> trainList) {

}
