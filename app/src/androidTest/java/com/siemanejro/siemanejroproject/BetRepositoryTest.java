package com.siemanejro.siemanejroproject;

import com.siemanejro.siemanejroproject.model.RoomBet;

import java.util.Calendar;

public class BetRepositoryTest {
    public static RoomBet createBet() {
        return new RoomBet(109L, 2L, Calendar.getInstance().getTime().toString(), 3L, null, 3);
    }

}
