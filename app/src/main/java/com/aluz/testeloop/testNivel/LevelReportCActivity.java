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
    Button continueQuestionsLevelC;
    ImageButton backHomeC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_reportc);
        continueQuestionsLevelC = findViewById(R.id.btnContinueLevelC);
        continueQuestionsLevelC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionsC = new Intent(getApplicationContext(), CQuestionActivity.class);
                startActivity(questionsC);
            }
        });
        backHomeC = findViewById(R.id.imgbBackHomeC);
        backHomeC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });
    }
}