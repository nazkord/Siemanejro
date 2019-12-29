package com.siemanejro.siemanejroproject.roomService;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public interface BetDao {
    @Insert
    void insertAll(RoomBet... bets);

    @Delete
    void delete(RoomBet roomBet);

    @Update
    void updateUsers(RoomBet... bets);

    @Query("SELECT * FROM bet WHERE utcDate == :date")
    List<RoomBet> getBetsByDate(String date);
}
