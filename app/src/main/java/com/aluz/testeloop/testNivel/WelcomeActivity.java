package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aluz.testeloop.R;
import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.User;
import com.aluz.testeloop.testNivel.GuideActivity;
import com.aluz.testeloop.testNivel.QuestionTestActivity;

public class WelcomeActivity extends AppCompatActivity {

    AppCompatButton BotaoWelcomeTestLevel;
    TextView Welcome;
    String nameWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        InitFind();

        //setando o nome do jogador logado
        Bundle bundle = getIntent().getExtras();
        nameWelcome = bundle.getString("nameWelcome");
        Welcome.setText(nameWelcome);

        BotaoWelcomeTestLevel.setOnClickListener(view -> {
            Intent retornar = new Intent(getApplicationContext(), GuideActivity.class);
            retornar.putExtra("nameLogin", nameWelcome);
            startActivity(retornar);
        });
    }
    public void InitFind(){
        BotaoWelcomeTestLevel = findViewById(R.id.btnWelcomeTestLevel);
        Welcome = findViewById(R.id.txvNameWelcome);
    }
}