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
import com.aluz.testeloop.password.ValidActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button BotaoRegisterNewUser, BotaoLogin;
    TextView BotaoForgotPassword;
    TextInputEditText InputNameUserLogin, InputUserPassword;
    CheckBox RememberPassword;
    TextView gamer, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitFind();

        BotaoRegisterNewUser.setOnClickListener(view -> {
            Intent cadastro = new Intent(getApplicationContext(), AccountActivity.class);
            startActivity(cadastro);
        });

        BotaoForgotPassword.setOnClickListener(view -> {
            Intent redefinir = new Intent(getApplicationContext(), ValidActivity.class);
            startActivity(redefinir);
        });

        BotaoLogin.setOnClickListener(view -> {
            if(InputNameUserLogin.getText().toString().isEmpty() ||
                    InputUserPassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Insira os dados!", Toast.LENGTH_LONG).show();
            }else {
                DataBaseSQLite db = new DataBaseSQLite(this);
                try {
                    User user = db.validarLogin(InputNameUserLogin.getText().toString());
                    if (user.getPassword() != null) {
                        if (user.getPassword().equals(InputUserPassword.getText().toString())) {
                            User nivel = db.selecionarNivel(user.getLogin());
                            if(!nivel.getNivel().equals("0")) {
                                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                                home.putExtra("nameLogin", user.getLogin());
                                startActivity(home);
                            }
                            else {
                                Intent welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                                Log.e("*****************",  user.getLogin() );
                                welcome.putExtra("nameWelcome", user.getLogin());
                                startActivity(welcome);
                            }
                        } else {
                            Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                }
            }
       });

        InputUserPassword.setVisibility(View.VISIBLE);
        RememberPassword.setOnClickListener(view ->{
            if(RememberPassword.isChecked()){
                InputUserPassword.setVisibility(View.VISIBLE);
            }else
                InputUserPassword.setVisibility(View.INVISIBLE);
        });
    }
    public void InitFind(){
        InputNameUserLogin = findViewById(R.id.edtTxtNameUser);
        InputUserPassword = findViewById(R.id.edtTxtPasswordUser);
        RememberPassword = findViewById(R.id.checkRememberPassword);
        gamer = findViewById(R.id.txvAvatarNameHome);
        points = findViewById(R.id.tvxUserPointsHome);
        BotaoRegisterNewUser = findViewById(R.id.btnRegister);
        BotaoForgotPassword = findViewById(R.id.tvForgotPassword);
        BotaoLogin = findViewById(R.id.btnLogin);
    }

}