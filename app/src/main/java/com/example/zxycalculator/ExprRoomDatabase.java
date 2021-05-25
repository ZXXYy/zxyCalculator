package com.example.zxycalculator;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Expression.class}, version = 1, exportSchema = false)
public abstract class ExprRoomDatabase extends RoomDatabase {
    public abstract ExprDao exprDao();
    private static ExprRoomDatabase INSTANCE;

    public static ExprRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExprRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExprRoomDatabase.class, "expr_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
