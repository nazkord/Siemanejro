package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Match implements Serializable {
    private LocalDateTime matchDate;
    private String homeTeam;
    private String awayTeam;
    private Integer result1;
    private Integer result2;
    private int id;
    private String utcDate;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    //constructor
    public Match(LocalDateTime matchDate, String homeTeam, String team2) {
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = team2;
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

    public String getMatchDateToString() {
        return getMatchDate().format(dateTimeFormatter);
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public String getTeam1() {
        return homeTeam;
    }

    public void setTeam1(String team1) {
        this.homeTeam = team1;
    }

    public String getTeam2() {
        return awayTeam;
    }

    public void setTeam2(String team2) {
        this.awayTeam = team2;
    }

    public int getId() {
        return id;
    }

    public String getUtcDate() {
        return utcDate;
    }
}
