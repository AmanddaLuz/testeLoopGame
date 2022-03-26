package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    AppCompatButton BotaoStartTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        BotaoStartTest = findViewById(R.id.btnRedefinir);

        BotaoStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retornar = new Intent(getApplicationContext(),TesteNivelActivity.class);
                startActivity(retornar);
            }
        });
    }
}