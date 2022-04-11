package com.aluz.testeloop.testNivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
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
    String name, finalPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_test);
        InitFind();

        //Resgatando o nome do jogador logado
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("nameGuide");
        finalPoints = bundle.getString("finalPoints");
        if (finalPoints == null){
            finalPoints = " ";
        }
//        Log.e("*********", String.valueOf(finalPoints));
        finalizar.setOnClickListener(v -> {
            timer.cancel();
            validateLevel();
        });

        botaoBack.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(),
                    "Avaliação não concluída.\n Conclua essa etapa para prosseguir!",
                    Toast.LENGTH_LONG).show();
            timer.cancel();
            Intent home = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(home);
        });

        //carregando dados
        questionsList.add(new QuestionClassA("TESTE - SOBRE O GITHUB - \n A que se refere o comando git commit -m?", "Limpar o terminal.","visualizar os logs dos arquivos gravados no repositório.","Refere-se ao termo \"mensseger\". Mensagem que será gravada no commit","Arquivo para ser criado no git e evitar que determinados arquivos sejam adicionados.", "Resumo dos commits feitos no projeto.", "Refere-se ao termo \"mensseger\". Mensagem que será gravada no commit"));
        questionsList.add(new QuestionClassA("TESTE - SOBRE O GITHUB - \n A que se refere o comando git branch?", "Comando para listar as branchs existentes.","Limpar o terminal.","Iniciar o git no terminal.","Enviar o diretório local para o central. ", "Consultar o status dos commits.", "Comando para listar as branchs existentes."));
        questionsList.add(new QuestionClassA("TESTE - SOBRE O GITHUB - \n O comando git add . refere-se a que?", "Visualizar os commits prontos par o git push.","Peparar um arquivo específico para ser commitado.","Limpar o terminal.","Encontrar a pessoa responsável pelo último commit.", "Adicionar todos os arquivos para serem commitados.", "Adicionar todos os arquivos para serem commitados."));
        questionsList.add(new QuestionClassA("TESTE - SOBRE O GITHUB - \n O comando git push refere-se a que?", "Adicionar os arquivos para serem commitados.","Enviar as alterações do repositório local para o repositório remoto.","Verificar o status dos commits.","Limpar o terminal.", "Mesclar as alterações em sua branch à branch master", "Enviar as alterações do repositório local para o repositório remoto."));
        questionsList.add(new QuestionClassA("TESTE - SOBRE O GITHUB - \n A que se refere o comando git pull?", "Atualiza o repositório local com a versão do repositório remoto! ",".Net","Kotlin","Limpar o terminal.", "Iniciar o git no terminal.", "Atualiza o repositório local com a versão do repositório remoto! "));

        //iniciar contador
        counter = 0;
        pointcounter = 0;
        loadQuestions(counter);

        txvTimer.setText("2'");
        timer = new CountDownTimer(300 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txvTimer.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                Toast.makeText(QuestionTestActivity.this, "Acabou o tempo!", Toast.LENGTH_SHORT).show();
                timer.cancel();
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

    };

    //função que valida para qual nível o jogador será direcionado
    public void validateLevel(){
        Log.e("*************", String.valueOf(pointcounter));
        if (finalPoints.equals("300")) {
            timer.cancel();
            Intent performance = new Intent(getApplicationContext(),PerformanceActivity.class);
            performance.putExtra("nameLogin", name);
            startActivity(performance);
        }
        else
        {
            if (pointcounter >= 0 && pointcounter <= 40) {
                timer.cancel();
                levelUp(name, "1");
                Intent congratsA = new Intent(getApplicationContext(), LevelReportAActivity.class);
                congratsA.putExtra("reportA", name);
                startActivity(congratsA);
            }
            else if (pointcounter > 40 && pointcounter <= 70){
                timer.cancel();
                levelUp(name, "2");
                Intent congratsB = new Intent(getApplicationContext(), LevelReportBActivity.class);
                congratsB.putExtra("reportB", name);
                startActivity(congratsB);
            }
            else {
                timer.cancel();
                levelUp(name, "3");
                Intent congratsC = new Intent(getApplicationContext(), LevelReportCActivity.class);
                congratsC.putExtra("reportC", name);
                startActivity(congratsC);
            }
        }
    }

    //função que atualiza o nível do jogador no banco de dados
    public void levelUp(String name, String nivel){
        User player = new User();
        player.setNivel(nivel);
        player.setLogin(name);
        DataBaseSQLite db = new DataBaseSQLite(this);
        db.atualizarNivel(player);
        //Toast.makeText(this, "Falha ao atualizar!", Toast.LENGTH_LONG).show();

    }

    //Identificando as variáveis
    public void InitFind(){
        botaoBack = findViewById(R.id.imgButtonBackLogin);
        txvQuestions = findViewById(R.id.txvQuestionTestLevel);
        txvQuestionsCount = findViewById(R.id.txvNumberQuestionTestLevel);
        txvTimer = findViewById(R.id.txvTimerTestLevel);
        point = findViewById(R.id.txvPontuacaoTestLevel);
        alternativeA = findViewById(R.id.btnAlternativeA);
        alternativeB = findViewById(R.id.btnAlternativeB);
        alternativeC = findViewById(R.id.btnAlternativeC);
        alternativeD = findViewById(R.id.btnAlternativeD);
        alternativeE = findViewById(R.id.btnAlternativeE);
        finalizar = findViewById(R.id.btnStopTest);
    }
}