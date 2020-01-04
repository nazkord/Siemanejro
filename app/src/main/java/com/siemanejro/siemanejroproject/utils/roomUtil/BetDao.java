package com.siemanejro.siemanejroproject.utils.roomUtil;

import com.siemanejro.siemanejroproject.model.RoomBet;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RoomBet> bets);

    @Query("DELETE FROM bet")
    void deleteAll();

    @Update
    void update(List<RoomBet> roomBets);

    @Query("SELECT * FROM bet WHERE utcDate == :date")
    List<RoomBet> getBetsByDate(String date);

    @Query("SELECT * FROM bet WHERE  matchId == :matchId")
    RoomBet getBetByMatchId(Long matchId);
}
