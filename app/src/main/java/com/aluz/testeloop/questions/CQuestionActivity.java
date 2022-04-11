package com.aluz.testeloop.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aluz.testeloop.HomeActivity;
import com.aluz.testeloop.R;
import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.QuestionClassA;
import com.aluz.testeloop.modle.User;
import com.aluz.testeloop.testNivel.QuestionTestActivity;

import java.util.ArrayList;

public class CQuestionActivity extends AppCompatActivity {

    ImageButton botaoBackC;
    TextView txvQuestionsCountC, txvTimerC, txvQuestionsC, pointC;
    Button alternativeA, alternativeB, alternativeC, alternativeD, alternativeE;
    int counter, pointcounter;
    CountDownTimer timer;
    ArrayList<QuestionClassA> questionsList = new ArrayList<>();
    String nameHome, pointsHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cquestion);
        InitFind();

        Bundle bundle = getIntent().getExtras();
        nameHome = bundle.getString("nameHome");

        pointsHome = bundle.getString("pointsHome");

        pointC.setText("Pontos: " + pointsHome);

        botaoBackC.setOnClickListener(v -> {
            timer.cancel();
            Intent home = new Intent(getApplicationContext(), HomeActivity.class);
            home.putExtra("nameLogin", nameHome);
            startActivity(home);


        });

        //carregando dados
        questionsList.add(new QuestionClassA("Qual recurso a seguir, permite exibir uma mensagem na tela do usuário.?", "loge","logd","Intent intent = new Intent();","Toast", "public void", "Toast"));
        questionsList.add(new QuestionClassA("Qual das alternativas a seguir\n permite ajustar o tamanho do text?", "\"android:textAllCaps=\"true\".","android:layout_height=\"wrap_content\".","android:layout_marginStart=\"10dp\".","android:text=\"Amanda\".","android:textSize=\"16dp\".","android:textSize=\"16dp\"."));
        questionsList.add(new QuestionClassA("Qual alternativa define melhor o método findViewById? ", "Permite localizar um objeto através de um id.","Permite invocar um evento onclik.","Envia para tela o Text informado.","Declarar uma nova variável", "Converter um int em uma String.", "Permite localizar um objeto através de um id."));
        questionsList.add(new QuestionClassA("Intent intent = new Intent refere-se a qual ação?", "Invocar uma nova Activity.","Setar um text na tela do usuário.","Abrir um contador de tempo.","Fazer um comentário.", "Declarar uma nova variável do tipo String.", "Invocar uma nova Activity."));
        questionsList.add(new QuestionClassA("Qual dos métodos a seguir permite um evento click?", ".getText()",".setText()",".setOnClickListner",".setVisibility", ".getOnClickListner", ".setOnClickListner"));

        //iniciar contador
        counter = 0;
        pointcounter = Integer.parseInt(pointsHome);
        loadQuestions(counter);

        txvTimerC.setText("30");
        timer = new CountDownTimer(30 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txvTimerC.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                timer.cancel();
                Toast.makeText(CQuestionActivity.this, "Acabou o tempo!", Toast.LENGTH_SHORT).show();
                if (counter < (questionsList.size() - 1)){
                    counter++;
                }else{
                    counter = 0;
                }
                loadQuestions(counter);
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
            validatePoint();
            pointC.setText("Pontos: " + pointcounter);
            android.widget.Toast.makeText(CQuestionActivity.this, "Alternativa correta!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(CQuestionActivity.this, "Alternativa errada!",
                    Toast.LENGTH_SHORT).show();
        }
        if (counter < (questionsList.size() - 1)){
            counter++;
        }else{
            counter = 0;
            Toast.makeText(CQuestionActivity.this, "Nível concluído", Toast.LENGTH_SHORT).show();
            timer.cancel();
        }
        timer.cancel();
        loadQuestions(counter);
    };

    public void validatePoint(){
        pointUp(nameHome, pointcounter);
        if(pointcounter == 300){
            timer.cancel();
            levelUp(nameHome, "3");
            Intent testeFinal = new Intent(getApplicationContext(), QuestionTestActivity.class);
            testeFinal.putExtra("nameGuide",nameHome);
            testeFinal.putExtra("finalPoints", String.valueOf(pointcounter));
            Log.e("********77777****", String.valueOf(pointcounter));
            startActivity(testeFinal);}
    }
    public void pointUp(String name, int point){
        User player = new User();
        player.setPontuacao(String.valueOf(point));
        player.setLogin(name);
        DataBaseSQLite db = new DataBaseSQLite(this);
        db.atualizarPontos(player);
    }
    public void levelUp(String name, String nivel){
        User player = new User();
        player.setNivel(nivel);
        player.setLogin(name);
        DataBaseSQLite db = new DataBaseSQLite(this);
        db.atualizarNivel(player);
    }

    public void InitFind(){
        botaoBackC = findViewById(R.id.imgButtonBackC);
        txvQuestionsC = findViewById(R.id.tvQuestionC);
        txvQuestionsCountC = findViewById(R.id.tvNumberQuestionC);
        txvTimerC = findViewById(R.id.tvTimerC);
        pointC = findViewById(R.id.tvPontuacaoC);
        alternativeA = findViewById(R.id.btnAlternativeA);
        alternativeB = findViewById(R.id.btnAlternativeB);
        alternativeC = findViewById(R.id.btnAlternativeC);
        alternativeD = findViewById(R.id.btnAlternativeD);
        alternativeE = findViewById(R.id.btnAlternativeE);
    }
}