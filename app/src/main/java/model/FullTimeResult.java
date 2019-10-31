package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullTimeResult implements Serializable {
    private Long id;
    private Integer homeTeam;
    private Integer awayTeam;

    public FullTimeResult(Long id, Integer homeTeam, Integer awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public FullTimeResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }

    public void setHomeTeam(Integer homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Integer awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return "FullTimeResult{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
