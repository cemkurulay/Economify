package com.cem.kurulay.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class politicianActivity extends AppCompatActivity {

    String currency;
    int exchangeRate;
    TextView tv;
    MediaPlayer mp;
    int[] sounds = {R.raw.dollar2,R.raw.dollar4,R.raw.dollar6,R.raw.dollar8,R.raw.dollar10};
    int[] soundsEuro = {R.raw.euro2,R.raw.euro4,R.raw.euro6,R.raw.euro8,R.raw.euro10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv = findViewById(R.id.politicianCurrencyTextView);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        currency = b.getString("currency");
        exchangeRate = b.getInt("exchangeRate");

        tv.setText(tv.getText() + currency + exchangeRate);
    }

    public void onListenPoliticianClick(View view) {
        if(currency.equalsIgnoreCase("$")){
            mp = MediaPlayer.create(this, sounds[(exchangeRate/2)-1]);
            mp.start();
        }else{
            mp = MediaPlayer.create(this, soundsEuro[(exchangeRate/2)-1]);
            mp.start();
        }
    }

    public void onReadPoliticianClick(View view) {
        Toast.makeText(this, R.string.toastTempMessage, Toast.LENGTH_LONG).show();
    }
}