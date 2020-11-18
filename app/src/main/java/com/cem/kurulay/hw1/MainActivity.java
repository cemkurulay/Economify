package com.cem.kurulay.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cem.kurulay.hw1_newpackage.publicActivity;

public class MainActivity extends AppCompatActivity {

    Spinner currencyTypeSpinner;
    ImageView currencyImageView;
    SeekBar exchangeRateSeekBar;
    Button politiciansButton;
    Button publicButton;
    TextView exchangeRateTextView;
    String currentCurrency;
    int currentExchangeRate;
    TextView foreignMoneyAmountView;
    int foreignMoneyAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        currencyTypeSpinner = findViewById(R.id.currencySpinner);
        currencyImageView = findViewById(R.id.currencyImageView);
        exchangeRateSeekBar = findViewById(R.id.exchangeRateSeekBar);
        politiciansButton = findViewById(R.id.politicianSaysButton);
        publicButton = findViewById(R.id.publicSaysButton);
        exchangeRateTextView = findViewById(R.id.exchangeSeekBarTextView);
        foreignMoneyAmountView = findViewById(R.id.foreignCurrencyAmountTextView);
        currentExchangeRate = 2;
        currencyTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    currentCurrency = "£";
                    currencyImageView.setImageResource(R.drawable.ic_eurosymbol);
                }else{
                    currentCurrency = "$";
                    currencyImageView.setImageResource(R.drawable.ic_dollarsymbol);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exchangeRateSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentExchangeRate = progress*2 + 2;
                String currentExchangeRateText = "";
                currentExchangeRateText += currentExchangeRate;
                exchangeRateTextView.setText("Exchange rate: " + currentExchangeRateText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void onPoliticianClick(View view) {
        Intent intent = new Intent(this, politicianActivity.class);
        Bundle b = new Bundle();
        b.putInt("exchangeRate", currentExchangeRate);
        b.putString("currency", currentCurrency);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onPublicClick(View view) {
        Intent intent = new Intent(this, publicActivity.class);
        Bundle b = new Bundle();
        b.putInt("exchangeRate", currentExchangeRate);
        b.putString("currency", currentCurrency);
        intent.putExtras(b);
        startActivity(intent);
    }
    private void makeAndShowDialogBox(String message) {
        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);
        myDialogBox.setTitle("Converted Amount in Turkish Lira");
        myDialogBox.setMessage(message);
        myDialogBox.setPositiveButton("Close",
        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        myDialogBox.create();
        myDialogBox.show();
    }

    public void onConvertMoneyClick(View view) {
        if(foreignMoneyAmountView.getText().toString().isEmpty()){
            makeAndShowDialogBox("Do not try to break the app please :)");
        }else{
            foreignMoneyAmount = Integer.parseInt(foreignMoneyAmountView.getText().toString());
            String convertedAmount = "" + foreignMoneyAmount*currentExchangeRate + " ₺";
            makeAndShowDialogBox(convertedAmount);
        }
    }
}