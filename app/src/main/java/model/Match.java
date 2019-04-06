package model;

import java.io.Serializable;

public class Match implements Serializable {
    private String utcDate;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    private String id;

    public String getUtcDate() {
        return utcDate;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public String getId() {
        return id;
    }
}
