package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aluz.testeloop.R;

public class GuideActivity extends AppCompatActivity {

    Button BotaoProntoTest;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("nameLogin");

        BotaoProntoTest = findViewById(R.id.btnTest);
        BotaoProntoTest.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), QuestionTestActivity.class);
            intent.putExtra("nameGuide", name);
            startActivity(intent);

        });
    }
}