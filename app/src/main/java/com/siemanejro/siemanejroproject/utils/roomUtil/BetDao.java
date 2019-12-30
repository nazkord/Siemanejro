package com.siemanejro.siemanejroproject.utils.roomUtil;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BetDao {
    @Insert
    void insertAll(List<RoomBet> bets);

    @Delete
    void delete(RoomBet roomBet);

    @Update
    void updateUsers(RoomBet... bets);

    @Query("SELECT * FROM bet WHERE utcDate == :date")
    List<RoomBet> getBetsByDate(String date);
}
