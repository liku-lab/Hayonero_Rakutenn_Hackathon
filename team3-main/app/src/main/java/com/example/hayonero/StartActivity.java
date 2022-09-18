package com.example.hayonero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    public static final String GET_POINT
            = "com.example.hayonero.getpoint";

    private boolean isSuccess = true;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intentMain = getIntent();
        String sleepTime = intentMain.getStringExtra(MainActivity.SLEEP_TIME);

        TextView setTime = findViewById(R.id.setTime);
        setTime.setText("設定した時刻(" + sleepTime + ")");

        Button giveUpBtn = findViewById(R.id.GiveUpButton);
        giveUpBtn.setOnClickListener(v -> {
            isSuccess = false;
            Intent intent = new Intent(getApplication(), AchievementFailureActivity.class);
            startActivity(intent);
        });

        TimerTask task = new TimerTask() {
            public void run() {
                if (isSuccess) {
                    isFinish = true;
                    Intent intent = new Intent(getApplication(), AchievementSuccessActivity.class);
                    intent.putExtra(StartActivity.GET_POINT, getSuccessPoint(sleepTime));
                    startActivity(intent);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 10000);
    }

    private int getSuccessPoint(String sleepTime) {
        String hourStr = sleepTime.split(":")[0];
        int hour = Integer.parseInt(hourStr);
        if (hour < 19) {
            return 0;
        } else if (hour < 22) {
            return 3;
        } else if (hour < 23) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isFinish) {
            Intent intent = new Intent(getApplication(), AchievementFailureActivity.class);
            startActivity(intent);
            isSuccess = false;
        }
    }
}