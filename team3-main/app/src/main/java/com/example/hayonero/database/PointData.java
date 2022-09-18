package com.example.hayonero.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "point_data")
public class PointData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "point")
    private int point;

    // id = 0 にすれば自動でidが割り当てられる
    public PointData(int id, int point) {
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public int getPoint() {
        return point;
    }
}