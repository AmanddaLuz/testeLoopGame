package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpLevelBActivity extends AppCompatActivity {
    Button startQuestionsLevelB, backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_report);

        startQuestionsLevelB.findViewById(R.id.btnContinueQuestionsLevelB);
        startQuestionsLevelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questions = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(questions);

            }
        });
        backHome.findViewById(R.id.ivArrowBackHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(home);
            }
        });
    }
}