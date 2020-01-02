package com.siemanejro.siemanejroproject.utils.roomUtil;

import android.content.Context;

import com.siemanejro.siemanejroproject.model.RoomBet;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomService {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    //TODO: I think that is not the best way to do it
    public static void insertBets(List<RoomBet> bets, Context context) {
        BetDao betDao = BetDatabase.getInstance(context).getBetDao();
        executorService.submit(() -> bets.forEach(roomBet -> {
            if(betDao.getBetById(roomBet.getId()) != null) {
                betDao.updateBets(Collections.singletonList(roomBet));
            }
        }));
    }

    public static RoomBet getBetByMatchId(Long matchId, Context context) {
        BetDao betDao = BetDatabase.getInstance(context).getBetDao();
//        Executors.newSingleThreadExecutor().execute(() -> betWrapper.setBet(betDao.getBetByMatchId(matchId)));
        return new RoomBet();
    }
}
