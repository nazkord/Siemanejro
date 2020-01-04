package com.siemanejro.siemanejroproject.utils.roomUtil;

import android.content.Context;
import android.os.AsyncTask;

import com.siemanejro.siemanejroproject.SiemanejroApp;
import com.siemanejro.siemanejroproject.model.RoomBet;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RoomService {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void insertBets(List<RoomBet> bets, Context context) {
        BetDao betDao = BetDatabase.getInstance(context).getBetDao();
        executorService.submit(() -> betDao.insert(bets));
    }

    public static List<RoomBet> getBetsByDate(String date, Context context) {
        BetDao betDao = BetDatabase.getInstance(context).getBetDao();

        FutureTask<List<RoomBet>> future =
                new FutureTask<>(() -> {
                    try {
                        return betDao.getBetsByDate(date);
                    } catch (Exception e) {
                        System.out.println(
                                "Error while getting bets from Room : " + e.getMessage());
                        return null;
                    }
                });
        executorService.execute(future);

        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

//    private class LoadHepler extends AsyncTask<String, Void, List<RoomBet>> {
//
//
//        @Override
//        protected List<RoomBet> doInBackground(String... strings) {
//            BetDao betDao = BetDatabase.getInstance(SiemanejroApp.getContext()).getBetDao();
//            return betDao.getBetsByDate(strings[0]);
//        }
//    }
}
