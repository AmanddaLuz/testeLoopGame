package com.aluz.testeloop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.User;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button BotaoRegisterNewUser, BotaoLogin;
    TextView BotaoForgotPassword;
    TextInputEditText InputNameUserLogin, InputUserPassword;
    CheckBox RememberPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BotaoRegisterNewUser = findViewById(R.id.btnRegister);
        BotaoRegisterNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(cadastro);
            }
        });
        BotaoForgotPassword = findViewById(R.id.tvForgotPassword);
        BotaoForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redefinir = new Intent(getApplicationContext(), ValidActivity.class);
                startActivity(redefinir);
            }
        });
        BotaoLogin = findViewById(R.id.btnLogin);
        BotaoLogin.setOnClickListener(view -> {
            DataBaseSQLite db = new DataBaseSQLite(this);
            try{
                User user1 = db.selecionarUser(InputNameUserLogin.getText().toString());

                if (user1.getPassword() != null){
                    if(user1.getPassword().equals(InputUserPassword.getText().toString())){

                        Intent logar = new Intent(getApplicationContext(), HomeActivity.class);
                               startActivity(logar);
                    }else {
                        Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                    }
                }else {

                    Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
            }


        });
        InputNameUserLogin = findViewById(R.id.edtTxtNameUser);
        InputUserPassword = findViewById(R.id.edtTxtPasswordUser);
        RememberPassword = findViewById(R.id.checkRememberPassword);

        InputUserPassword.setVisibility(View.VISIBLE);
//        DataBaseSQLite db = new DataBaseSQLite(this);
//        User user = db.selecionarUserPorID("1");
//
//        InputNameUserLogin.setText(user.getLogin());
//        InputUserPassword.setText(user.getPassword());

        RememberPassword.setOnClickListener(view ->{
            if(RememberPassword.isChecked()){
                InputUserPassword.setVisibility(View.VISIBLE);
            }else
                InputUserPassword.setVisibility(View.INVISIBLE);
        });


    }
}