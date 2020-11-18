package com.cem.kurulay.hw1_newpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.cem.kurulay.hw1.R;

public class publicActivity extends AppCompatActivity {
    String currency;
    int exchangeRate;
    TextView tv;
    MediaPlayer mp;
    int[] sounds = {R.raw.dolar2p,R.raw.dolar4p,R.raw.dolar6p,R.raw.dolar8p,R.raw.dolar10p};
    int[] soundsEuro = {R.raw.euro2p,R.raw.euro4p,R.raw.euro6p,R.raw.euro8p,R.raw.euro10p};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv = findViewById(R.id.publicCurrencyTextView);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        currency = b.getString("currency");
        exchangeRate = b.getInt("exchangeRate");

        tv.setText(tv.getText() + currency + exchangeRate);
    }


    public void onListenPublicClick(View view) {
        if(currency.equalsIgnoreCase("$")){
            mp = MediaPlayer.create(this, sounds[(exchangeRate/2)-1]);
            mp.start();
        }else{
            mp = MediaPlayer.create(this, soundsEuro[(exchangeRate/2)-1]);
            mp.start();
        }
    }

    public void onReadPublicClick(View view) {
        Toast.makeText(this, R.string.toastTempMessage, Toast.LENGTH_LONG).show();
    }
}