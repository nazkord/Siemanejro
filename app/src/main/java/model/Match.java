package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable {
    private Long id;
    private Competition competition;
    private String utcDate;
    private Integer matchday;
    private FootballTeam homeTeam;
    private FootballTeam awayTeam;
    private Score score;
    private String status;

    public Competition getCompetition() {
        return competition;
    }

    public String getStatus() {
        return status;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public Long getId() {
        return id;
    }
}
