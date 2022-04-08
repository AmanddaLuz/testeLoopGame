package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aluz.testeloop.LoginActivity;
import com.aluz.testeloop.R;
import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.QuestionClassA;
import com.aluz.testeloop.modle.User;

import java.util.ArrayList;

public class QuestionTestActivity extends AppCompatActivity {
    ImageButton botaoBack;
    TextView txvQuestionsCount, txvTimer, txvQuestions, point;
    Button alternativeA, alternativeB, alternativeC, alternativeD, alternativeE, finalizar;
    int counter, pointcounter;
    CountDownTimer timer;
    ArrayList<QuestionClassA> questionsList = new ArrayList<>();
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_test);
        InitFind();
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("nameGuide");

        Log.d("aaaaaaa", name);

        botaoBack.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(),
                    "Avaliação não concluída.\n Conclua essa etapa para prosseguir!",
                    Toast.LENGTH_LONG).show();
            Intent home = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(home);
            timer.cancel();
        });

        //carregando dados
        questionsList.add(new QuestionClassA("TESTE - Escolha um nome", "Amanda","Bruno","Cátia","Danielle", "Ester", "Amanda"));
        questionsList.add(new QuestionClassA("TESTE - Qual fruta abaixo é verde?", "Abacate","Banana","Caqui","damasco", "Escarola", "Abacate"));
        questionsList.add(new QuestionClassA("TESTE - Quantos meses tem um ano?", "2 meses","6 meses","12 meses","24 meses", "36 meses", "12 meses"));
        questionsList.add(new QuestionClassA("TESTE - Quantos dias tem um ano bissexto?", "265","366","367","368", "369", "366"));
        questionsList.add(new QuestionClassA("TESTE - Qual a linguagem mais legal?", "Java",".Net","Kotlin","Cobol", "Python", "Python"));

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
                validateLevel();
            }
        };
        timer.start();
    }

    // função de carga das perguntas
    public void loadQuestions(int n){
        final QuestionClassA q = questionsList.get(n);
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
            Toast.makeText(QuestionTestActivity.this, "Nível concluído!", Toast.LENGTH_SHORT).show();
            validateLevel();
        }
        finalizar.setOnClickListener(v -> validateLevel());
    };

    public void validateLevel(){
        if (pointcounter >= 0 && pointcounter <= 40) {
            levelUp(name, "1");
            Intent congratsA = new Intent(getApplicationContext(), LevelReportAActivity.class);
            congratsA.putExtra("reportA", name);
            startActivity(congratsA);
        }
        else if (pointcounter > 40 && pointcounter <= 70){
            levelUp(name, "2");
            Intent congratsB = new Intent(getApplicationContext(), LevelReportBActivity.class);
            congratsB.putExtra("reportB", name);
            startActivity(congratsB);
        }
        else {
            levelUp(name, "3");
            Intent congratsC = new Intent(getApplicationContext(), LevelReportCActivity.class);
            congratsC.putExtra("reportC", name);
            startActivity(congratsC);
        }
    }
    public void levelUp(String name, String nivel){
        User player = new User();
        player.setNivel(nivel);
        player.setLogin(name);
        DataBaseSQLite db = new DataBaseSQLite(this);
        db.atualizarNivel(player);

        //Toast.makeText(this, "Falha ao atualizar!", Toast.LENGTH_LONG).show();

    }
    public void InitFind(){
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
    }




}