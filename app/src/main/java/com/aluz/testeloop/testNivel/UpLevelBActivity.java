package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.questions.AQuestionActivity;
import com.aluz.testeloop.R;
import com.aluz.testeloop.questions.BQuestionActivity;

public class UpLevelBActivity extends AppCompatActivity {
 //   Button startQuestionsLevelB;
    ImageView backHome;
    String nameHome, pointsHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_nivel_bactivity);
        InitFind();

        Bundle bundle = getIntent().getExtras();
        nameHome = bundle.getString("nameHome");
        pointsHome = bundle.getString("pointsHome");

//        startQuestionsLevelB.setOnClickListener(v -> {
//            Intent questions = new Intent(getApplicationContext(), BQuestionActivity.class);
//            questions.putExtra("nameHome", nameHome);
//            questions.putExtra("pointsHome", pointsHome);
//            startActivity(questions);
//
//        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                home.putExtra("nameLogin", nameHome);
                startActivity(home);
            }
        });
    }
    public void InitFind() {
        backHome = findViewById(R.id.ivArrowBackHomeB);
//        startQuestionsLevelB = findViewById(R.id.btnContinueQuestionsLevelB);
}
}