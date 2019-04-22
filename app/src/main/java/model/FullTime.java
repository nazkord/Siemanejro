package model;

import java.io.Serializable;

public class FullTime implements Serializable {
    private int homeTeam;
    private int awayTeam;

    public int getHomeTeam() {
        return homeTeam;
    }

    public int getAwayTeam() {
        return awayTeam;
    }
}
