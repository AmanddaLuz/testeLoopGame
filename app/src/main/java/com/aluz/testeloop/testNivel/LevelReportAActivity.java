package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.questions.AQuestionActivity;
import com.aluz.testeloop.R;

public class LevelReportAActivity extends AppCompatActivity {
    ImageButton backHomeA;
    String nameLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_reporta);
        Bundle bundle = getIntent().getExtras();
        nameLogin = bundle.getString("reportA");

        backHomeA = findViewById(R.id.imgbBackHomeA);
        backHomeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                home.putExtra("nameLogin", nameLogin);
                startActivity(home);
            }
        });
    }
}