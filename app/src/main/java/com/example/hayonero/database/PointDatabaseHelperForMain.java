package com.example.hayonero.database;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.hayonero.AchievementSuccessActivity;
import com.example.hayonero.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PointDatabaseHelperForMain {
    private PointDao pDao;
    private int totalPoints = 0;
    final Handler mHandler = new Handler();
    private MainActivity mActivity;
    private boolean isSuccess = false;

    public PointDatabaseHelperForMain(Context context, MainActivity activity) {
        PointDatabase pDb = PointDatabaseSingleton.getInstance(context);
        this.pDao = pDb.pointDao();
        this.mActivity = activity;
    }

    public void setTotalPoints(Button pointBtn) {
        isSuccess = false;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //データベース関連は非同期処理で行う
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    totalPoints = pDao.getPoint();
                    isSuccess = true;
                } catch (Exception e) {
                    Log.d("databaseDebug", e.getMessage());
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!isSuccess) {
                            mActivity.displayMessage("データの読み込みに失敗しました。");
                        } else {
                            pointBtn.setText("現在獲得ポイント\n" + totalPoints);
                        }
                    }
                });
            }
        });
    }
}
