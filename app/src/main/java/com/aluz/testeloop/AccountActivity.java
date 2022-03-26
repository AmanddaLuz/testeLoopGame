package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AccountActivity extends AppCompatActivity {

    ImageView BotaoCancel;
    ImageView BotaoChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BotaoCancel = findViewById(R.id.ivCancel);

        BotaoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cancelarCadastro = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(cancelarCadastro);
            }
        });

        BotaoChecked = findViewById(R.id.ivChecked);

        BotaoChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent confirmarCadastro = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(confirmarCadastro);

            }
        });
    }
}