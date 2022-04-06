package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent performance = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(performance);
            }
        });
    }
}