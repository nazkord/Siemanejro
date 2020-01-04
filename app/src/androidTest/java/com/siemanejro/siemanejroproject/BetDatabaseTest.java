package com.siemanejro.siemanejroproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.siemanejro.siemanejroproject.utils.roomUtil.BetDao;
import com.siemanejro.siemanejroproject.utils.roomUtil.BetDatabase;
import com.siemanejro.siemanejroproject.model.RoomBet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import androidx.room.Room;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BetDatabaseTest {

    private BetDao betDao;
    private BetDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();
        db = Room.inMemoryDatabaseBuilder(context, BetDatabase.class).build();
        betDao = db.getBetDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        RoomBet bet = BetRepositoryTest.createBet();
        betDao.insert(Collections.singletonList(bet));
        List<RoomBet> betsByDate = betDao.getBetsByDate(bet.getUtcDate());
        assertEquals(betsByDate.get(0), bet);
    }

    @Test
    public void insertingConflictTest() throws Exception {
        RoomBet bet = BetRepositoryTest.createBet();
        betDao.insert(Collections.singletonList(bet));
        RoomBet bet2 = BetRepositoryTest.createBet();
        bet2.setResult(5); //different than in bet
        betDao.insert(Collections.singletonList(bet2));
        List<RoomBet> betsByDate = betDao.getBetsByDate(bet.getUtcDate());
        assertEquals(betsByDate.get(0), bet2);
    }
}
