package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangePasswordActivity extends AppCompatActivity {

    AppCompatButton BotaoConfirm;
    ImageView BackLogin;
    TextView InputCode, InputPasswordReset, InputConfirmNewPassword;

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
        BackLogin = findViewById(R.id.ivBackLogin);
        BackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
            }
        });
        InputCode = findViewById(R.id.edtTxtCodeInput);
        InputPasswordReset = findViewById(R.id.edtTxtPasswordReset);
        InputConfirmNewPassword = findViewById(R.id.edtTxtConfirmNewPassowrd);
    }
}