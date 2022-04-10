package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.modle.Ranking;
import com.aluz.testeloop.modle.User;
import com.aluz.testeloop.questions.AQuestionActivity;
import com.aluz.testeloop.questions.BQuestionActivity;
import com.aluz.testeloop.questions.CQuestionActivity;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button BotaoJogar;
    ImageButton BotaoLogoutGame;
    TextView AvatarUserName, PointsUser, FirstPlayer, SecondPlayer, ThirdPlayer;
    TextView PointsFirstPlayer, PointsSecondPlayer, PointsThirdPlayer, hintsHome, txvtime;
    CountDownTimer timer;
    int count = 0;
    String nameLogin;
    User jogador;
    DataBaseSQLite db = new DataBaseSQLite(this);
    ArrayList<Ranking> ranking = new ArrayList<Ranking>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitFind();

        ranking = db.rankingList();
        Log.e("**************", String.valueOf(ranking.get(0).getLogin()));
        Log.e("**************", String.valueOf(ranking.get(1).getLogin()));
        Log.e("**************", String.valueOf(ranking.get(2).getLogin()));



        //mandando o nome do usuário pra próxima intent
        Bundle bundle = getIntent().getExtras();
        nameLogin = bundle.getString("nameLogin");

        //Instanciando o banco de dados

        jogador = db.selecionarUser(nameLogin);

        BotaoJogar.setOnClickListener(view -> {
            timer.cancel();
            if(jogador.getNivel().equals("1")) {

                Intent inicio = new Intent(getApplicationContext(), AQuestionActivity.class);
                inicio.putExtra("nameHome", nameLogin);
                inicio.putExtra("pointsHome", String.valueOf(jogador.getPontuacao()));
                startActivity(inicio);
            }
            if(jogador.getNivel().equals("2")){
                Intent inicio = new Intent(getApplicationContext(), BQuestionActivity.class);
                inicio.putExtra("nameHome", nameLogin);
                inicio.putExtra("pointsHome", String.valueOf(jogador.getPontuacao()));
                startActivity(inicio);
            }
            if (jogador.getNivel().equals("3")){
                Intent inicio = new Intent(getApplicationContext(), CQuestionActivity.class);
                inicio.putExtra("nameHome", nameLogin);
                inicio.putExtra("pointsHome", jogador.getPontuacao());
                Log.e("****************", String.valueOf(jogador.getPontuacao()));
                startActivity(inicio);
            }
        });

        // Setando as variáveis do login
        AvatarUserName.setText(jogador.getLogin());
        PointsUser.setText(jogador.getPontuacao());
        FirstPlayer.setText(ranking.get(0).getLogin());
        PointsFirstPlayer.setText(ranking.get(0).getPontuacao());
        SecondPlayer.setText(ranking.get(1).getLogin());
        PointsSecondPlayer.setText(ranking.get(1).getPontuacao());
        ThirdPlayer.setText(ranking.get(2).getLogin());
        PointsThirdPlayer.setText(ranking.get(2).getPontuacao());


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
//                Toast.makeText(HomeActivity.this, "Próxima Dica!", Toast.LENGTH_SHORT).show();
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
        PointsThirdPlayer = findViewById(R.id.txvPointsThirdPlayer);
        hintsHome = findViewById(R.id.txvHintsHome);
        txvtime = findViewById(R.id.txvtime);
        BotaoJogar = findViewById(R.id.btnStartPlay);
        BotaoLogoutGame = findViewById(R.id.imgButtonBack);
    }
    }

