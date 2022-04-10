package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.questions.AQuestionActivity;
import com.aluz.testeloop.R;
import com.aluz.testeloop.questions.BQuestionActivity;

public class LevelReportBActivity extends AppCompatActivity {

    ImageButton backHomeB;
    String nameLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_reportb);
        Bundle bundle = getIntent().getExtras();
        nameLogin = bundle.getString("reportB");

        backHomeB = findViewById(R.id.imgbBackHomeB);
        backHomeB.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
            home.putExtra("nameLogin", nameLogin);
            startActivity(home);
        });
    }
}