package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    TextView tvPoints, tvPersonalBest;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int points = getIntent().getExtras().getInt("Points");
        tvPoints = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        tvPoints.setText(""+ points);
        sharedPreferences = getSharedPreferences("pref", 0);
        int pointsSP = sharedPreferences.getInt("pointsSP", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(points > pointsSP){
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            editor.commit();

        }
        tvPersonalBest.setText("" + pointsSP);

    }

    public void restart(View view) {
        Intent nextlevel = new Intent(GameOverActivity.this, StartActivity.class);
        startActivity(nextlevel);
        finish();
    }

    public void back(View view) {
        finish();
    }
}