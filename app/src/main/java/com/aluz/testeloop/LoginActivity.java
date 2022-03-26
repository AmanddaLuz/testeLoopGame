package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button BotaoCadastrar;
    TextView BotaoForgotPassword;
    Button BotaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BotaoCadastrar = findViewById(R.id.btnCadastrar);

        BotaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(cadastro);
            }
        });

        BotaoForgotPassword = findViewById(R.id.tvForSenha);

        BotaoForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redefinir = new Intent(getApplicationContext(), ValidActivity.class);
                startActivity(redefinir);
            }
        });

        BotaoLogin = findViewById(R.id.btnLogin);

        BotaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logar = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(logar);
            }
        });
    }
}