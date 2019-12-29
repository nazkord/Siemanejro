package com.siemanejro.siemanejroproject.roomService;

import com.siemanejro.siemanejroproject.model.Score;

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
}

