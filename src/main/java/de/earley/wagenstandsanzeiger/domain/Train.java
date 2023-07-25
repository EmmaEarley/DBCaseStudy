package de.earley.wagenstandsanzeiger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Train(
        //List of trainNumbers as String to catch trainNumbers such as "403/406" and "Halbzug"
        @JsonProperty("trainNumbers") List<String> trainNumberList,
        @JsonProperty("waggons") List<Waggon> waggonList
        ){
}
