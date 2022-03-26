package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChangePasswordActivity extends AppCompatActivity {

    AppCompatButton BotaoConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        BotaoConfirm = findViewById(R.id.btnRedefinir);

        BotaoConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ok = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(ok);
            }
        });
    }
}