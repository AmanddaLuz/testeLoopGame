package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aluz.testeloop.R;

public class GuideActivity extends AppCompatActivity {

    Button BotaoProntoTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        BotaoProntoTest = findViewById(R.id.btnTest);

        BotaoProntoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionTestActivity.class);
                startActivity(intent);

            }
        });
    }
}