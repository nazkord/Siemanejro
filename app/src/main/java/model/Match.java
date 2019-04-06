package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Match implements Serializable {
    private LocalDateTime matchDate;
    private String team1;
    private String team2;
    private Integer result1;
    private Integer result2;
    private int id;
    private String utcDate;

    //constructor
    public Match(LocalDateTime matchDate, String team1, String team2) {
        this.matchDate = matchDate;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Integer getResult1() {
        return result1;
    }

    public void setResult1(Integer result1) {
        this.result1 = result1;
    }

    public Integer getResult2() {
        return result2;
    }

    public void setResult2(Integer result2) {
        this.result2 = result2;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getId() {
        return id;
    }

    public String getUtcDate() {
        return utcDate;
    }
}
