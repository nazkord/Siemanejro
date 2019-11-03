package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Score implements Serializable {
    private Long id;
    private String winner;
    private FullTimeResult fullTime;

    public Score(Long id, String winner, FullTimeResult fullTime) {
        this.id = id;
        this.winner = winner;
        this.fullTime = fullTime;
    }

    public Score() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(winner, score.winner) &&
                Objects.equals(fullTime, score.fullTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winner, fullTime);
    }
}
