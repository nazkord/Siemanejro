package com.siemanejro.siemanejroproject;

import com.siemanejro.siemanejroproject.utils.roomUtil.RoomBet;

import java.util.Calendar;

public class BetRepositoryTest {
    public static RoomBet createBet() {
        return new RoomBet(1L, 2L, Calendar.getInstance().getTime().toString(), 3L, null, 3);
    }

}
