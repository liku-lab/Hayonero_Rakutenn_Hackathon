package com.example.hayonero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hayonero.database.PointDatabaseHelperForMain;
import com.example.hayonero.database.PointDatabaseHelperForSuccess;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public static final String SLEEP_TIME
            = "com.example.hayonero.sleeptime";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startButton);
        startBtn.setOnClickListener(v -> {
            String setTime = textView.getText().toString();
            String hourStr = setTime.split(":")[0];
            int hour = Integer.parseInt(hourStr);
            if (hour >= 19) {
                Intent intent = new Intent(getApplication(), StartActivity.class);
                intent.putExtra(MainActivity.SLEEP_TIME, getSleepTime());
                startActivity(intent);
            } else {
                setTimeFailDialog(this);
            }
        });

        Button informationBtn = findViewById(R.id.informationButton);
        informationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), InformationActivity.class);
            startActivity(intent);
        });

        Button rankingBtn = findViewById(R.id.rankingButton);
        rankingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), RankingActivity.class);
            startActivity(intent);
        });

        Button pointBtn = findViewById(R.id.pointButton);
        pointBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            startActivity(intent);
        });

        PointDatabaseHelperForMain pointDatabaseHelper = new PointDatabaseHelperForMain(getApplication(), this);
        pointDatabaseHelper.setTotalPoints(pointBtn);

        textView = findViewById(R.id.sleepTime);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void displayMessage(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();  //トーストを表示
    }

    private String getSleepTime() {
        return textView.getText().toString();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        String minuteStr = "" + minute;
        if (minute < 9) minuteStr = 0 + minuteStr;
        textView.setText(hourOfDay + ":" + minuteStr);
    }

    public void setTimeFailDialog(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("エラー")
                .setMessage("19:00～23:59で設定してください")
                .setPositiveButton("OK", null)
                .show();
    }

}