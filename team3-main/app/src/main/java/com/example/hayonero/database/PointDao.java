package com.example.hayonero.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PointDao {
    @Insert
    void insert(PointData pointData);

    @Query("SELECT SUM(point) FROM point_data")
    int getPoint();
}

