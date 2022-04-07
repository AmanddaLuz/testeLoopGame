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
    Button continueQuestionsLevelB;
    ImageButton backHomeB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_reportb);
        continueQuestionsLevelB = findViewById(R.id.btnContinueLevelB);
        continueQuestionsLevelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionB = new Intent(getApplicationContext(), BQuestionActivity.class);
                startActivity(questionB);
            }
        });
        backHomeB = findViewById(R.id.imgbBackHomeB);
        backHomeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });
    }
}