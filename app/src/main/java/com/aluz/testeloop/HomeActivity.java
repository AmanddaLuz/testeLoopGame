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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.aluz.testeloop.dataBase.DataBaseSQLite;
import com.aluz.testeloop.LoginActivity;
import com.aluz.testeloop.modle.User;

public class HomeActivity extends AppCompatActivity {

    Button BotaoJogar;
    ImageButton BotaoLogoutGame;
    TextView AvatarUserName, PointsUser, FirstPlayer, SecondPlayer, ThirdPlayer;
    TextView PointsFirstPlayer, PointsSecondPlayer, PointsThirdPlayer, hintsHome, txvtime;
    CountDownTimer timer;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BotaoJogar = findViewById(R.id.btnStartPlay);
        BotaoJogar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent inicio = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(inicio);
                timer.cancel();

            }
        });
        BotaoLogoutGame = findViewById(R.id.imgButtonBack);
        BotaoLogoutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), ConfirmLogoutActivity.class);
                startActivity(logout);
                timer.cancel();

            }
        });
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


    }

