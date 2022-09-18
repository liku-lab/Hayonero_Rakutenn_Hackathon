package com.example.hayonero.database;

import android.content.Context;

import androidx.room.Room;


public class PointDatabaseSingleton {
    private static PointDatabase instance = null;

    public static PointDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }

        instance = Room.databaseBuilder(context,
                PointDatabase.class, "point_database").build();
        return instance;
    }
}

