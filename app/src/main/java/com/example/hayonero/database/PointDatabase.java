package com.example.hayonero.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PointData.class}, version = 1, exportSchema = false)
public abstract class PointDatabase extends RoomDatabase {
    public abstract PointDao pointDao();
}
