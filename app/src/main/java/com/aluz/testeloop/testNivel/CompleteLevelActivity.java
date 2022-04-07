package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aluz.testeloop.questions.AQuestionActivity;
import com.aluz.testeloop.R;

public class CompleteLevelActivity extends AppCompatActivity {
    Button PerformanceTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_level);
        PerformanceTest.findViewById(R.id.btnPerformanceTest);
        PerformanceTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent performance = new Intent(getApplicationContext(), AQuestionActivity.class);
                startActivity(performance);
            }
        });
    }
}