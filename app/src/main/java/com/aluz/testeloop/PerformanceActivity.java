package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PerformanceActivity extends AppCompatActivity {
    Button BackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        BackToHome.findViewById(R.id.btnBackToHome);
        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });
    }
}