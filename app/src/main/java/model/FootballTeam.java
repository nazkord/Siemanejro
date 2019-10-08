package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FootballTeam implements Serializable {

    private Long id;
    public String name;

    public String getName() {
        return name;
    }
}
