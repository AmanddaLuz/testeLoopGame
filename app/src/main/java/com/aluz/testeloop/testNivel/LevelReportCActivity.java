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
import com.aluz.testeloop.questions.CQuestionActivity;

public class LevelReportCActivity extends AppCompatActivity {

    ImageButton backHomeC;
    String nameLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_reportc);
        Bundle bundle = getIntent().getExtras();
        nameLogin = bundle.getString("reportC");

        backHomeC = findViewById(R.id.imgbBackHomeC);
        backHomeC.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
            home.putExtra("nameLogin", nameLogin);
            startActivity(home);
        });
    }
}