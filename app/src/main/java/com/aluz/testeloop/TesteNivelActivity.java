package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TesteNivelActivity extends AppCompatActivity {

    Button BotaoProntoTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_nivel);

        BotaoProntoTest = findViewById(R.id.btnTest);

        BotaoProntoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(test);
            }
        });
    }
}