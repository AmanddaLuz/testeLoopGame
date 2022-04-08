package com.aluz.testeloop.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.R;
import com.aluz.testeloop.modle.QuestionClassA;

import java.util.ArrayList;

public class CQuestionActivity extends AppCompatActivity {

    ImageButton botaoBackC;
    TextView txvQuestionsCountC, txvTimerC, txvQuestionsC, pointC;
    Button alternativeA, alternativeB, alternativeC, alternativeD, alternativeE;
    int counter, pointcounter;
    CountDownTimer timer;
    ArrayList<QuestionClassA> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cquestion);
        InitFind();

        botaoBackC.setOnClickListener(v -> {
            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(home);
            timer.cancel();
        });

        //carregando dados
        questionsList.add(new QuestionClassA("  NIVEL C - Escolha um nome", "Amanda","Bruno","Cátia","Danielle", "Ester", "Amanda"));
        questionsList.add(new QuestionClassA("  NIVEL C - Qual fruta abaixo é verde?", "Abacate","Banana","Caqui","damasco", "Escarola", "Abacate"));
        questionsList.add(new QuestionClassA("  NIVEL C - Quantos meses tem um ano?", "2 meses","6 meses","12 meses","24 meses", "36 meses", "12 meses"));
        questionsList.add(new QuestionClassA("  NIVEL C - Quantos dias tem um ano bissexto?", "265","366","367","368", "369", "366"));
        questionsList.add(new QuestionClassA("  NIVEL C - Qual a linguagem mais legal?", "Java",".Net","Kotlin","Cobol", "Python", "Python"));

        //iniciar contador
        counter = 0;
        pointcounter = 0;
        loadQuestions(counter);

        txvTimerC.setText("30");
        timer = new CountDownTimer(30 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txvTimerC.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                Toast.makeText(CQuestionActivity.this, "Acabou o tempo!", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }

    // função de carga das perguntas
    public void loadQuestions(int n){
        final QuestionClassA q = questionsList.get(n);
        txvQuestionsCountC.setText((n + 1) + "/" + questionsList.size());

        txvTimerC.setText("" + 30);
        if(timer != null){
            timer.start();
        }
        txvQuestionsC.setText("" + q.getQue());
        alternativeA.setText("" + q.getAlternativeA());
        alternativeB.setText("" + q.getAlternativeB());
        alternativeC.setText("" + q.getAlternativeC());
        alternativeD.setText("" + q.getAlternativeD());
        alternativeE.setText("" + q.getAlternativeE());

        alternativeA.setOnClickListener(v -> tryTeste(alternativeA.getText().toString(),q.getCorrectAlter()));
        alternativeB.setOnClickListener(v -> tryTeste(alternativeB.getText().toString(),q.getCorrectAlter()));
        alternativeC.setOnClickListener(v -> tryTeste(alternativeC.getText().toString(),q.getCorrectAlter()));
        alternativeD.setOnClickListener(v -> tryTeste(alternativeD.getText().toString(),q.getCorrectAlter()));
        alternativeE.setOnClickListener(v -> tryTeste(alternativeE.getText().toString(),q.getCorrectAlter()));


    }

    // função de validação da resposta
    void tryTeste (String response, String CorrectAlternative){
        if (response.equals(CorrectAlternative)) {
            pointcounter = pointcounter + 10;
            pointC.setText("Pontos: " + pointcounter);
            android.widget.Toast.makeText(CQuestionActivity.this, "Alternativa correta!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(CQuestionActivity.this, "Alternativa errada!",
                    Toast.LENGTH_SHORT).show();
        }
        if (counter < (questionsList.size() - 1)){
            timer.cancel();
            counter++;
            loadQuestions(counter);
        }else{
            Toast.makeText(CQuestionActivity.this, "Nível concluído", Toast.LENGTH_SHORT).show();
        }
    };

    public void InitFind(){
        botaoBackC = findViewById(R.id.imgButtonBackC);
        txvQuestionsC = findViewById(R.id.tvQuestionC);
        txvQuestionsCountC = findViewById(R.id.tvNumberQuestionC);
        txvTimerC = findViewById(R.id.tvTimerC);
        pointC = findViewById(R.id.tvPontuacao);
        alternativeA = findViewById(R.id.btnAlternativeA);
        alternativeB = findViewById(R.id.btnAlternativeB);
        alternativeC = findViewById(R.id.btnAlternativeC);
        alternativeD = findViewById(R.id.btnAlternativeD);
        alternativeE = findViewById(R.id.btnAlternativeE);

    }

}