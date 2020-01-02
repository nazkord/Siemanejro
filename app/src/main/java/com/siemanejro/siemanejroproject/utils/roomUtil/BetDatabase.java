package com.siemanejro.siemanejroproject.utils.roomUtil;

import android.content.Context;

import com.siemanejro.siemanejroproject.model.RoomBet;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = RoomBet.class, version = 1, exportSchema = false)
public abstract class BetDatabase extends RoomDatabase {

    private static BetDatabase instance;
    private static final String DATABASE_NAME = "bet_db";

    public static synchronized BetDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                        BetDatabase.class, BetDatabase.DATABASE_NAME)
                        .build();
        }
        return instance;
    }

    public abstract BetDao getBetDao();
}
