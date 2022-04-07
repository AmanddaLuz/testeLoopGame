package com.aluz.testeloop.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aluz.testeloop.R;
import com.aluz.testeloop.password.ChangePasswordActivity;
import com.google.android.material.textfield.TextInputEditText;

public class ValidActivity extends AppCompatActivity {

    AppCompatButton BotaoSend;
    TextInputEditText InputUserEmailValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid);
        BotaoSend = findViewById(R.id.btnSendCode);
        BotaoSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retornar = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(retornar);
            }
        });
        InputUserEmailValid = findViewById(R.id.edtTxtEmailUser);
    }
}