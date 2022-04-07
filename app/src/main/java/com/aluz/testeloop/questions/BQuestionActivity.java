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

public class BQuestionActivity extends AppCompatActivity {

    ImageButton botaoBackB;
    TextView txvQuestionsCountB, txvTimerB, txvQuestionsB, pointB;
    Button alternativeA, alternativeB, alternativeC, alternativeD, alternativeE;

    int counter, pointcounter;

    CountDownTimer timer;

    ArrayList<QuestionClassA> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bquestion);

        botaoBackB = findViewById(R.id.imgButtonBackB);
        txvQuestionsB = findViewById(R.id.tvQuestionB);
        txvQuestionsCountB = findViewById(R.id.tvNumberQuestionB);
        txvTimerB = findViewById(R.id.tvTimerB);
        pointB = findViewById(R.id.tvPontuacaoB);
        alternativeA = findViewById(R.id.btnAlternativeA);
        alternativeB = findViewById(R.id.btnAlternativeB);
        alternativeC = findViewById(R.id.btnAlternativeC);
        alternativeD = findViewById(R.id.btnAlternativeD);
        alternativeE = findViewById(R.id.btnAlternativeE);

        botaoBackB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
                timer.cancel();
            }
        });

        //carregando dados
        questionsList.add(new QuestionClassA("  NÍVEL B - Escolha um nome", "Amanda","Bruno","Cátia","Danielle", "Ester", "Amanda"));
        questionsList.add(new QuestionClassA("  NÍVEL B - Qual fruta abaixo é verde?", "Abacate","Banana","Caqui","damasco", "Escarola", "Abacate"));
        questionsList.add(new QuestionClassA("  NÍVEL B - Quantos meses tem um ano?", "2 meses","6 meses","12 meses","24 meses", "36 meses", "12 meses"));
        questionsList.add(new QuestionClassA("  NÍVEL B - Quantos dias tem um ano bissexto?", "265","366","367","368", "369", "366"));
        questionsList.add(new QuestionClassA("  NÍVEL B - Qual a linguagem mais legal?", "Java",".Net","Kotlin","Cobol", "Python", "Python"));

        //iniciar contador
        counter = 0;
        pointcounter = 0;
        loadQuestions(counter);

        txvTimerB.setText("30");
        timer = new CountDownTimer(30 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txvTimerB.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                Toast.makeText(BQuestionActivity.this, "Acabou o tempo!", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }

    // função de carga das perguntas
    public void loadQuestions(int n){
        final QuestionClassA q = questionsList.get(n);
        txvQuestionsCountB.setText((n + 1) + "/" + questionsList.size());

        txvTimerB.setText("" + 30);
        if(timer != null){
            timer.start();
        }
        txvQuestionsB.setText("" + q.getQue());
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
            pointB.setText("Pontos: " + pointcounter);
            android.widget.Toast.makeText(BQuestionActivity.this, "Alternativa correta!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(BQuestionActivity.this, "Alternativa errada!",
                    Toast.LENGTH_SHORT).show();
        }
        if (counter < (questionsList.size() - 1)){
            timer.cancel();
            counter++;
            loadQuestions(counter);
        }else{
            Toast.makeText(BQuestionActivity.this, "Nível concluído", Toast.LENGTH_SHORT).show();
        }
    };

    // função de chamada da proxima tela
    void proximaTela (Class newact){
        Intent home = new Intent(getApplicationContext(), newact);
        startActivity(home);
    }

}