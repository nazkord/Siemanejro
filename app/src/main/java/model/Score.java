package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Score implements Serializable {
    private Long id;
    private String winner;
    private FullTimeResult fullTime;

    public String getWinner() {
        return winner;
    }

    public FullTimeResult getFullTime() {
        return fullTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setFullTime(FullTimeResult fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", winner='" + winner + '\'' +
                ", fullTime=" + fullTime +
                '}';
    }
}
