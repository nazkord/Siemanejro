package com.siemanejro.siemanejroproject.utils.roomUtil;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.Score;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bet")
public class RoomBet {

    @PrimaryKey
    @ColumnInfo(name = "betId")
    private Long id;

    private Long matchId;
    private String utcDate;
    private Long userId;
    @Embedded
    private Score userScore;
    private Integer result;

    public RoomBet(Long id, Long matchId, String utcDate, Long userId, Score userScore, Integer result) {
        this.id = id;
        this.matchId = matchId;
        this.utcDate = utcDate;
        this.userId = userId;
        this.userScore = userScore;
        this.result = result;
    }

    public static List<RoomBet> transformTo(List<Bet> bets) {
        return bets.stream()
                .map(bet -> new RoomBet(bet.getId(), bet.getMatch().getId(), bet.getMatch().getUtcDate(),
                        bet.getUser().getId(), bet.getUserScore(), bet.getResult()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Score getUserScore() {
        return userScore;
    }

    public Integer getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomBet roomBet = (RoomBet) o;
        return id.equals(roomBet.id) &&
                Objects.equals(matchId, roomBet.matchId) &&
                Objects.equals(utcDate, roomBet.utcDate) &&
                Objects.equals(userId, roomBet.userId) &&
                Objects.equals(userScore, roomBet.userScore) &&
                Objects.equals(result, roomBet.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId, utcDate, userId, userScore, result);
    }
}

