package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.R;
import com.aluz.testeloop.questions.CQuestionActivity;

public class UpLevelCActivity extends AppCompatActivity {
    ImageView backHome;
    String nameHome, pointsHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_level_cactivity);
        Bundle bundle = getIntent().getExtras();
        nameHome = bundle.getString("nameHome");
        pointsHome = bundle.getString("pointsHome");
        backHome=findViewById(R.id.imgvbackC);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                home.putExtra("nameLogin", nameHome);
                startActivity(home);
            }
        });
    }
}