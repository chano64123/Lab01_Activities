package com.example.PracticaCalificadaActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView edtResult;

    private Double priceFirstGame;
    private Double discountPrice;
    private Double lowerPrice;
    private Double budget;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();

        initVariables();

        calculateGames();

        edtResult.setText(result);
    }

    private void calculateGames() {
        result = getResources().getString(R.string.resultPart1);
        double save = 0;
        for (int i = 0; i< 10; i++){
            if (priceFirstGame - (discountPrice*i) >= lowerPrice){
                save = priceFirstGame - (discountPrice*i);
            } else {
                save = lowerPrice;
            }

            if (i!=9){
                result += save + ", ";
            } else {
                result += save;
            }

        }

        result += getResources().getString(R.string.resultPart2) + budget + getResources().getString(R.string.resultPart3);

        int quantityGames = 0;
        Double priceFinalGames = 0.0;
        String pricesGames = "";
        for (double i = priceFirstGame; priceFinalGames <= budget; i-=discountPrice){
            if (priceFinalGames + i > lowerPrice){
                pricesGames += i + " = ";
            } else {
                pricesGames += i + " + ";
            }
            quantityGames++;
            priceFinalGames += i;
            if (priceFinalGames > lowerPrice){
                break;
            }
        }

        result += quantityGames + getResources().getString(R.string.resultPart4) + pricesGames + priceFinalGames + getResources().getString(R.string.resultPart5);
    }

    private void initView() {
        edtResult = (TextView)findViewById(R.id.edtResult);
    }

    private void initVariables() {
        Intent intent = getIntent();
        priceFirstGame = intent.getDoubleExtra(Config.PRICE_FIRST_GAME,0);
        discountPrice = intent.getDoubleExtra(Config.DISCOUNT_PRICE,0);
        lowerPrice = intent.getDoubleExtra(Config.LOWER_PRICE,0);
        budget = intent.getDoubleExtra(Config.BUDGET,0);
    }
}