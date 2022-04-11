package com.aluz.testeloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ConfirmLogoutActivity extends AppCompatActivity {

    ImageView ConfirmLogout, CancelLogout;
    String nameHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_logout);
        Bundle bundle = getIntent().getExtras();
        nameHome= bundle.getString("nameHome");

        ConfirmLogout = findViewById(R.id.imgvConfirmLogout);
        CancelLogout = findViewById(R.id.imgvCancelLogout);

        ConfirmLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(backLogin);
            }
        });
        CancelLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(getApplicationContext(), HomeActivity.class);
                backHome.putExtra("nameHome", nameHome );
                startActivity(backHome);
            }
        });
    }
}