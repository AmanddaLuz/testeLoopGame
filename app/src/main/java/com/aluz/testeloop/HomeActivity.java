package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.User;
import com.aluz.testeloop.questions.AQuestionActivity;
import com.aluz.testeloop.questions.BQuestionActivity;
import com.aluz.testeloop.questions.CQuestionActivity;

public class HomeActivity extends AppCompatActivity {

    Button BotaoJogar;
    ImageButton BotaoLogoutGame;
    TextView AvatarUserName, PointsUser, FirstPlayer, SecondPlayer, ThirdPlayer;
    TextView PointsFirstPlayer, PointsSecondPlayer, PointsThirdPlayer, hintsHome, txvtime;
    CountDownTimer timer;
    int count = 0;
    String nameLogin;
    User jogador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitFind();
        Bundle bundle = getIntent().getExtras();
        nameLogin = bundle.getString("nameLogin");

        //Instanciando o banco de dados
        DataBaseSQLite db = new DataBaseSQLite(this);
        jogador = db.selecionarUser(nameLogin);

        Log.d("", jogador.getNivel());

        BotaoJogar.setOnClickListener(view -> {
            timer.cancel();
            if(jogador.getNivel().equals("1")) {

                Intent inicio = new Intent(getApplicationContext(), AQuestionActivity.class);
                inicio.putExtra("nameHome", nameLogin);
                startActivity(inicio);
            }
            if(jogador.getNivel().equals("2")){
                Intent inicio = new Intent(getApplicationContext(), BQuestionActivity.class);
                startActivity(inicio);
            }
            if (jogador.getNivel().equals("3")){
                Intent inicio = new Intent(getApplicationContext(), CQuestionActivity.class);
                startActivity(inicio);
            }
        });

        // Setando as variáveis do login
        AvatarUserName.setText(jogador.getLogin());
        PointsUser.setText(jogador.getPontuacao());

        BotaoLogoutGame.setOnClickListener(v -> {
            Intent logout = new Intent(getApplicationContext(), ConfirmLogoutActivity.class);
            startActivity(logout);
            timer.cancel();
        });



// Programando o tempo e as dicas na tela.
        hintsHome.setText("Ctrl + Alt + O ---> Limpa os imports inutilizados.");
        txvtime.setText("5");
        timer = new CountDownTimer(5 * 1000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                txvtime.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                Toast.makeText(HomeActivity.this, "Próxima Dica!", Toast.LENGTH_SHORT).show();
                Resources res = getResources();
                String[] hint = res.getStringArray(R.array.hintsHome);
                hintsHome.setText(hint[count]);
                if(count < hint.length - 1) {
                    count++;
                }else{
                    count = 0;
                }
                timer.start();
            }
        };
        timer.start();
    };
    public void InitFind(){
        AvatarUserName = findViewById(R.id.txvAvatarNameHome);
        PointsUser = findViewById(R.id.tvxUserPointsHome);
        FirstPlayer = findViewById(R.id.txvNameFirstPlayer);
        SecondPlayer = findViewById(R.id.txvNameSecondPlayer);
        ThirdPlayer = findViewById(R.id.txvNameThirdPlayer);
        PointsFirstPlayer = findViewById(R.id.txvPointsFirstPlayer);
        PointsSecondPlayer = findViewById(R.id.txvPointsSecondPlayer);
        PointsThirdPlayer = findViewById(R.id.txvNameThirdPlayer);
        hintsHome = findViewById(R.id.txvHintsHome);
        txvtime = findViewById(R.id.txvtime);
        BotaoJogar = findViewById(R.id.btnStartPlay);
        BotaoLogoutGame = findViewById(R.id.imgButtonBack);
    }
    }

