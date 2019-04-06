package model;

import java.io.Serializable;

public class FullTime implements Serializable {
    private int HomeTeam;
    private int AwayTeam;

    public int getHomeTeam() {
        return HomeTeam;
    }

    public int getAwayTeam() {
        return AwayTeam;
    }
}
