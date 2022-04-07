package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aluz.testeloop.testNivel.GuideActivity;
import com.aluz.testeloop.testNivel.QuestionTestActivity;

public class WelcomeActivity extends AppCompatActivity {

    AppCompatButton BotaoStartTestLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        BotaoStartTestLevel = findViewById(R.id.btnStartTestLevel);

        BotaoStartTestLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retornar = new Intent(getApplicationContext(), GuideActivity.class);
                startActivity(retornar);
            }
        });
    }
}