package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.R;

public class PerformanceActivity extends AppCompatActivity {
    Button BackToHome;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("nameLogin");

        setContentView(R.layout.activity_performance);
        BackToHome = findViewById(R.id.btnBackToHome);
        BackToHome.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
            home.putExtra("nameLogin" , name);
            startActivity(home);
        });
    }
}