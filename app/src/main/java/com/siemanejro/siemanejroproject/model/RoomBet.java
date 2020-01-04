package com.siemanejro.siemanejroproject.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import lombok.Data;

@Entity(tableName = "bet", indices = {@Index(value = {"matchId"},
        unique = true)})
@Data
public class RoomBet {

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "betId")
//    private Long localId;

    @PrimaryKey
    @ColumnInfo(name = "serverBetId")
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

    private static RoomBet transformFrom(Bet bet) {
        return new RoomBet(bet.getId(), bet.getMatch().getId(), bet.getMatch().getUtcDate().substring(0,10),
                Optional.ofNullable(bet.getUser()).map(User::getId).orElse(null), bet.getUserScore(), bet.getResult());
    }

    public static List<RoomBet> transformToListFrom(List<Bet> bets) {
        return bets.stream()
                .map(RoomBet::transformFrom)
                .collect(Collectors.toList());
    }
}
