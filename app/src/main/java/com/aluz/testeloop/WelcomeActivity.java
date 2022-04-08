package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.User;
import com.aluz.testeloop.testNivel.GuideActivity;
import com.aluz.testeloop.testNivel.QuestionTestActivity;

public class WelcomeActivity extends AppCompatActivity {

    AppCompatButton BotaoStartTestLevel;
    TextView Welcome;
    String nameWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        InitFind();
        Bundle bundle = getIntent().getExtras();
        nameWelcome = bundle.getString("nameWelcome");
        Log.e("*****************",  nameWelcome);
        Welcome.setText(nameWelcome);

        BotaoStartTestLevel.setOnClickListener(view -> {
            Intent retornar = new Intent(getApplicationContext(), GuideActivity.class);
            retornar.putExtra("nameLogin", nameWelcome);
            startActivity(retornar);
        });
    }
    public void InitFind(){
        BotaoStartTestLevel = findViewById(R.id.btnStartTestLevel);
        Welcome = findViewById(R.id.txvNameWelcome);
    }
}