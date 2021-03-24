package com.example.PracticaCalificadaActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtPriceFirstGame;
    private EditText edtDiscountPrice;
    private EditText edtLowerPrice;
    private EditText edtBudget;
    private Button btnCalculate;

    private AwesomeValidation validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initValidation();

        initListeners();
    }

    private void initListeners() {
        btnCalculate.setOnClickListener(this);
    }

    private void initValidation() {
        validate = new AwesomeValidation(ValidationStyle.BASIC);
        validate.addValidation(this, R.id.edtPriceFirstGame, RegexTemplate.NOT_EMPTY, R.string.errorEnterPrice);
        validate.addValidation(this, R.id.edtDiscountPrice, RegexTemplate.NOT_EMPTY, R.string.errorEnterPrice);
        validate.addValidation(this, R.id.edtLowerPrice, RegexTemplate.NOT_EMPTY, R.string.errorEnterPrice);
        validate.addValidation(this, R.id.edtBudget, RegexTemplate.NOT_EMPTY, R.string.errorEnterPrice);
    }

    private void initView() {
        edtPriceFirstGame = (EditText)findViewById(R.id.edtPriceFirstGame);
        edtDiscountPrice = (EditText)findViewById(R.id.edtDiscountPrice);
        edtLowerPrice = (EditText)findViewById(R.id.edtLowerPrice);
        edtBudget = (EditText)findViewById(R.id.edtBudget);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
    }

    @Override
    public void onClick(View v) {
        if (v != null){
            switch (v.getId()){
                case R.id.btnCalculate:
                    if (validate.validate()){
                        openActivity();
                    }
                    break;
            }
        }
    }

    private void openActivity() {
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(Config.PRICE_FIRST_GAME,Double.parseDouble(edtPriceFirstGame.getText().toString()));
        intent.putExtra(Config.DISCOUNT_PRICE,Double.parseDouble(edtDiscountPrice.getText().toString()));
        intent.putExtra(Config.LOWER_PRICE,Double.parseDouble(edtLowerPrice.getText().toString()));
        intent.putExtra(Config.BUDGET,Double.parseDouble(edtBudget.getText().toString()));
        startActivity(intent);
    }
}