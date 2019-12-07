package com.siemanejro.siemanejroproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bet {
    private Long id;
    private Match match;
    private User user;
    private Score userScore;
    private Integer result;

    public Bet() {
    }

    public Bet(Long id, Match match, User user, Score userScore, Integer result) {
        this.id = id;
        this.match = match;
        this.user = user;
        this.userScore = userScore;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Score getUserScore() {
        return userScore;
    }

    public void setUserScore(Score userScore) {
        this.userScore = userScore;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Objects.equals(id, bet.id) &&
                Objects.equals(match, bet.match) &&
                Objects.equals(user, bet.user) &&
                Objects.equals(userScore, bet.userScore) &&
                Objects.equals(result, bet.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match, user, userScore, result);
    }

    //    @Override
//    public String toString() {
//        return "Bet{" +
//                "id=" + id +
//                ", match=" + match +
//                ", user=" + user +
//                ", userScore=" + userScore +
//                ", result=" + result +
//                '}';
//    }
}
