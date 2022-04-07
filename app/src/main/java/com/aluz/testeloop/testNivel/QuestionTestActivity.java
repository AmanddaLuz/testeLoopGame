package com.aluz.testeloop.testNivel;

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
import com.aluz.testeloop.modle.QuestionClassTest;

import java.util.ArrayList;

public class QuestionTestActivity extends AppCompatActivity {
    ImageButton botaoBack;
    TextView txvQuestionsCount, txvTimer, txvQuestions, point;
    Button alternativeA, alternativeB, alternativeC, alternativeD, alternativeE, finalizar;

    int counter, pointcounter;

    CountDownTimer timer;

    ArrayList<QuestionClassTest> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_test);

        botaoBack = findViewById(R.id.iButtonBack);
        txvQuestions = findViewById(R.id.txvQuestionTestLevel);
        txvQuestionsCount = findViewById(R.id.txvNumberQuestionTestLevel);
        txvTimer = findViewById(R.id.txvTimerTestLevel);
        point = findViewById(R.id.txvPontuacaoLevel);
        alternativeA = findViewById(R.id.btnAlternativeA);
        alternativeB = findViewById(R.id.btnAlternativeB);
        alternativeC = findViewById(R.id.btnAlternativeC);
        alternativeD = findViewById(R.id.btnAlternativeD);
        alternativeE = findViewById(R.id.btnAlternativeE);
        finalizar = findViewById(R.id.btnStopTest);

        botaoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
                timer.cancel();
            }
        });

        //carregando dados
        questionsList.add(new QuestionClassTest("TESTE Escolha um nome", "Amanda","Bruno","Cátia","Danielle", "Ester", "Amanda"));
        questionsList.add(new QuestionClassTest("TESTE Qual fruta abaixo é verde?", "Abacate","Banana","Caqui","damasco", "Escarola", "Abacate"));
        questionsList.add(new QuestionClassTest("TESTE Quantos meses tem um ano?", "2 meses","6 meses","12 meses","24 meses", "36 meses", "12 meses"));
        questionsList.add(new QuestionClassTest("TESTE Quantos dias tem um ano bissexto?", "265","366","367","368", "369", "366"));
        questionsList.add(new QuestionClassTest("TESTE Qual a linguagem mais legal?", "Java",".Net","Kotlin","Cobol", "Python", "Python"));

        //iniciar contador
        counter = 0;
        pointcounter = 0;
        loadQuestions(counter);


        txvTimer.setText("2'");
        timer = new CountDownTimer(2000 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txvTimer.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                Toast.makeText(QuestionTestActivity.this, "Acabou o tempo!", Toast.LENGTH_SHORT).show();

            }

        };
        timer.start();




    }


    // função de carga das perguntas
    public void loadQuestions(int n){
        final QuestionClassTest q = questionsList.get(n);
        txvQuestionsCount.setText((n + 1) + "/" + questionsList.size());

        txvTimer.setText("" + 30);
        if(timer != null){
            timer.start();
        }
        txvQuestions.setText("" + q.getQue());
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
            point.setText("Pontos: " + pointcounter);
            android.widget.Toast.makeText(QuestionTestActivity.this, "Alternativa correta!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(QuestionTestActivity.this, "Alternativa errada!",
                    Toast.LENGTH_SHORT).show();
        }
        if (counter < (questionsList.size() - 1)){
            timer.cancel();
            counter++;
            loadQuestions(counter);
        }else{

            Toast.makeText(QuestionTestActivity.this, "Nível concluído. Clique em finalizar!", Toast.LENGTH_SHORT).show();

        }
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointcounter >= 0 && pointcounter <= 40) {
                    Intent congratsA = new Intent(getApplicationContext(), LevelReportAActivity.class);
                    startActivity(congratsA);
                }
                if (pointcounter > 40 && pointcounter <= 70){

                    //Toast.makeText(QuestionTestActivity.this, "ooooooooo", Toast.LENGTH_SHORT).show();
                    Intent congratsB = new Intent(getApplicationContext(), LevelReportBActivity.class);
                    startActivity(congratsB);
                }
                if (pointcounter > 70) {
                    //Toast.makeText(QuestionTestActivity.this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
                    Intent congratsC = new Intent(getApplicationContext(), LevelReportCActivity.class);
                    startActivity(congratsC);
                }
            }
        });



    };



    // função de chamada da proxima tela
    void proximaTela (Class newact){
        Intent home = new Intent(getApplicationContext(), newact);
        startActivity(home);
    }
}