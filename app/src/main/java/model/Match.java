package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable {
    private Long id;
    private String utcDate;
    private FootballTeam homeFootballTeam;
    private FootballTeam awayFootballTeam;
    private Score score;
    private String status;

    public String getStatus() {
        return status;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public FootballTeam getHomeFootballTeam() {
        return homeFootballTeam;
    }

    public FootballTeam getAwayFootballTeam() {
        return awayFootballTeam;
    }

    public Score getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }
}
