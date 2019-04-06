package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Match implements Serializable {
    private LocalDateTime matchDate;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    private String id;
    private String utcDate;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime getMatchDate() {
        return matchDate;
    }

    public String getMatchDateToString() {
        return getMatchDate().format(dateTimeFormatter);
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

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

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}
