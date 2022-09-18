package com.example.hayonero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hayonero.database.PointData;
import com.example.hayonero.database.PointDatabaseHelperForSuccess;

public class AchievementSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivement_success);

        Button goToMainInSuccessBtn = findViewById(R.id.GoToMainInSuccess);
        goToMainInSuccessBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });

        Intent intentStart = getIntent();
        int getPoint = intentStart.getIntExtra(StartActivity.GET_POINT, 0);

        TextView textView = findViewById(R.id.getPoints);
        textView.setText(getPoint + "ポイントをゲットした！");

        PointDatabaseHelperForSuccess pointDatabaseHelper = new PointDatabaseHelperForSuccess(getApplication(), this);
        pointDatabaseHelper.insertData(new PointData(0, getPoint));
    }

    @Override
    public void onBackPressed() {
    }
}