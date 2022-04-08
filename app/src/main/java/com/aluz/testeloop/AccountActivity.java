package com.aluz.testeloop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.User;

public class AccountActivity extends AppCompatActivity {

    ImageView BotaoCancelNewAccount, BotaoCheckedNewAccount;
    TextView InputNameNewUser, InputEmailNewUser, InputConfirmEmail, InputFoneNewUser, InputCEPNewUser, InputPasswordNewUser, InputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        BotaoCancelNewAccount = findViewById(R.id.ivCancelRegister);
        BotaoCancelNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cancelarCadastro = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(cancelarCadastro);
            }
        });

        BotaoCheckedNewAccount = findViewById(R.id.ivConfirmRegister);
        BotaoCheckedNewAccount.setOnClickListener(view -> {
                if(InputNameNewUser.getText().toString().isEmpty() ||
                InputPasswordNewUser.getText().toString().isEmpty()){
                    Toast.makeText(this, "Insira os dados!", Toast.LENGTH_LONG).show();
                }else {
                    DataBaseSQLite db = new DataBaseSQLite(this);
                    if (db.inserirUsuario(new User(
                            InputNameNewUser.getText().toString(),
                            InputPasswordNewUser.getText().toString(),
                            "50"))
                    ) {
                        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();


                        Intent confirmarCadastro = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(confirmarCadastro);
                    } else {
                        Toast.makeText(this, "Não foi possível realizar o cadastro!", Toast.LENGTH_LONG).show();
                    }
                }

        });
        InputNameNewUser = findViewById(R.id.edtTxtNameNewUser);
        InputEmailNewUser = findViewById(R.id.edtTxtEmailNewUser);
        InputConfirmEmail = findViewById(R.id.edtTxtConfirmNewEmail);
        InputCEPNewUser = findViewById(R.id.edtTxtCEPNewUser);
        InputPasswordNewUser = findViewById(R.id.edtTxtPasswordNewUser);
        InputConfirmPassword = findViewById(R.id.edtTxtConfirmPassword);
    }
}