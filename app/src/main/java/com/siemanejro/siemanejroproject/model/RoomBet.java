package com.siemanejro.siemanejroproject.model;

import java.util.List;
import java.util.stream.Collectors;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(tableName = "bet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBet {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "betId")
    private Long localId;

    @ColumnInfo(name = "serverBetId")
    private Long id;
    private Long matchId;
    private String utcDate;
    private Long userId;
    @Embedded
    private Score userScore;
    private Integer result;

    private static RoomBet transformFrom(Bet bet) {
        return new RoomBet(null, bet.getId(), bet.getMatch().getId(), bet.getMatch().getUtcDate(),
                        bet.getUser().getId(), bet.getUserScore(), bet.getResult());
    }

    public static List<RoomBet> transformListFrom(List<Bet> bets) {
        return bets.stream()
                .map(RoomBet::transformFrom)
                .collect(Collectors.toList());
    }
}
