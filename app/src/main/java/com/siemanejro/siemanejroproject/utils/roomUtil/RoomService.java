package com.siemanejro.siemanejroproject.utils.roomUtil;

import android.content.Context;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

public class RoomService {

    //TODO: I think that is not the best way to do it
    public static void insertBets(List<RoomBet> bets, Context context) {
        BetDao betDao = BetDatabase.getInstance(context).getBetDao();
        Executors.newSingleThreadExecutor().execute(() -> bets.forEach(roomBet -> {
            if(betDao.getBetById(roomBet.getId()) != null) {
                betDao.updateBets(Collections.singletonList(roomBet));
            }
        }));
    }
}
