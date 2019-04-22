package model;

import java.io.Serializable;


public class Score implements Serializable {
    private String winner;
    private FullTime fullTime;

    public String getWinner() {
        return winner;
    }

    public FullTime getFullTime() {
        return fullTime;
    }
}
