package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ValidActivity extends AppCompatActivity {

    AppCompatButton BotaoSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid);

        BotaoSend = findViewById(R.id.btnRedefinir);

        BotaoSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retornar = new Intent(getApplicationContext(),ChangePasswordActivity.class);
                startActivity(retornar);
            }
        });
    }
}