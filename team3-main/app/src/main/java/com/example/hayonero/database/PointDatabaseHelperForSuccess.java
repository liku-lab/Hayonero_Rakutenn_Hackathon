package com.example.hayonero.database;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hayonero.AchievementSuccessActivity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PointDatabaseHelperForSuccess extends AppCompatActivity {
    private PointDao pDao;
    private AchievementSuccessActivity asActivity;
    private boolean isSuccess = false;

    public PointDatabaseHelperForSuccess(Context context, AchievementSuccessActivity activity) {
        PointDatabase pDb = PointDatabaseSingleton.getInstance(context);
        this.pDao = pDb.pointDao();
        this.asActivity = activity;
    }

    public void insertData(PointData data) {
        //データベース関連は非同期処理で行う
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pDao.insert(data);
                } catch (Exception e) {
                }
            }
        });
    }
}

