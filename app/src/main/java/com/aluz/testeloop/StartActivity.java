package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//import com.aluz.loopgame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartActivity extends AppCompatActivity {
    ImageButton back;
    ImageView errorAlternative;
    ImageView trueAlternative;
    TextView tvTime;
    TextView tvPoints;
    TextView bodyQuestion;
    TextView tvResult;
    HashMap <String, Integer> map = new HashMap <String, Integer>();
    ArrayList <String> techList = new ArrayList <>();

//    Resources res = getResources();
//    String[] alternativeQ1 = res.getStringArray(R.array.alternativeQ1);

    int index;
    AppCompatButton alternativeA, alternativeB, alternativeC, alternativeD;
    int points;
    CountDownTimer countDownTimer;
    long millisUntilFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initFindViewById();
    }

    public void nextQuestion(View view) {
        countDownTimer.cancel();
        index++;
        if (index > techList.size() - 1) {
            ;
            bodyQuestion.setVisibility(View.GONE);
            alternativeA.setVisibility(View.GONE);
            alternativeB.setVisibility(View.GONE);
            alternativeC.setVisibility(View.GONE);
            alternativeD.setVisibility(View.GONE);
            Intent intent = new Intent(StartActivity.this, GameOverActivity.class);
            intent.putExtra("points", points);
            startActivity(intent);
            finish();
        } else {
            startGame();
        }
    }

    public void answerSelected(View view) {
        countDownTimer.cancel();
        String answer = ((Button)view).getText().toString().trim();
        String correctAnswer = techList.get(index);
        if(answer.equals(correctAnswer)){
            points++;
            tvPoints.setText(points + " / " + techList.size());
            tvResult.setText("Correct");

        } else {
            tvResult.setText("Wrong");
        }
    }

    void initFindViewById() {
        tvTime = findViewById(R.id.tvTime);
        tvPoints = findViewById(R.id.tvPoints);
        tvResult = findViewById(R.id.tvResult);
        back = findViewById(R.id.back);
        errorAlternative = findViewById(R.id.errorAlternative);
        trueAlternative = findViewById(R.id.ivConfirmRegister);
        bodyQuestion = findViewById(R.id.tvQuestion);
        alternativeA = findViewById(R.id.btnAlternativeA);
        alternativeB = findViewById(R.id.btnAlternativeB);
        alternativeC = findViewById(R.id.btnAlternativeC);
        alternativeD = findViewById(R.id.btnAlternativeD);

        index = 0;
        points = 0;
        millisUntilFinished = 1000;
        startGame();
        setTechList();
    }

    private void startGame() {
        millisUntilFinished = 10000;
        tvTime.setText(" " + (millisUntilFinished/1000) + "s");
        tvPoints.setText(points + " / " + techList.size());
        generateQuestions(index);
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText("" + (millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                index++;
                if(index > techList.size() - 1){
                    bodyQuestion.setVisibility(View.GONE);
                    alternativeA.setVisibility(View.GONE);
                    alternativeB.setVisibility(View.GONE);
                    alternativeC.setVisibility(View.GONE);
                    alternativeD.setVisibility(View.GONE);
                    Intent intent = new Intent(StartActivity.this, GameOverActivity.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    finish();
                } else {
                    startGame();
                }
                    {
                }
            }
        }.start();

    }

    private void generateQuestions(int index) {
        ArrayList<String> techListTemp = (ArrayList <String>) techList.clone();
        String correctAnswer = techList.get(index);
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        ArrayList<String> newList = new ArrayList<>();
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        newList.add(correctAnswer);
        Collections.shuffle(newList);
        alternativeA.setText(newList.get(0));
        alternativeB.setText(newList.get(1));
        alternativeC.setText(newList.get(2));
        alternativeD.setText(newList.get(3));
        bodyQuestion.setText(map.get(techList.get(index)));

    }

    void setTechList(){
        techList.add("Mercury");
        techList.add("Venus");
        techList.add("Earth");
        techList.add("Mars");
        techList.add("Amanda");
        techList.add("Danilo");
        techList.add("Alice");
        techList.add("Pandora");
        techList.add("Jack");
        techList.add("Kira");
        techList.add("1000");
        techList.add("100");
        techList.add("10");
        techList.add("1");
        techList.add("Ameixa");
        techList.add("Morango");
        techList.add("Maçã");
        techList.add("Banana");
        map.put(techList.get(0), R.string.q1);
        map.put(techList.get(1), R.string.q1);
        map.put(techList.get(2), R.string.q1);
        map.put(techList.get(3), R.string.q1);
        map.put(techList.get(4), R.string.q2);
        map.put(techList.get(5), R.string.q2);
        map.put(techList.get(6), R.string.q2);
        map.put(techList.get(7), R.string.q2);
        map.put(techList.get(8), R.string.q3);
        map.put(techList.get(9), R.string.q3);
        map.put(techList.get(10), R.string.q3);
        map.put(techList.get(11), R.string.q3);
        map.put(techList.get(12), R.string.q4);
        map.put(techList.get(13), R.string.q4);
        map.put(techList.get(14), R.string.q4);
        map.put(techList.get(15), R.string.q4);
        map.put(techList.get(16), R.string.q5);
        map.put(techList.get(17), R.string.q5);
        map.put(techList.get(18), R.string.q5);
        map.put(techList.get(19), R.string.q5);
        Collections.shuffle(techList);
    }
}