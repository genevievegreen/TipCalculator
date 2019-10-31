package com.green.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.green.tipcalculator.R.id.buttonCalculate;
import static com.green.tipcalculator.R.id.buttonMinus;
import static com.green.tipcalculator.R.id.buttonPlus;
import static com.green.tipcalculator.R.id.etBillAmount;
import static com.green.tipcalculator.R.id.tvPercentDisplay;
import static com.green.tipcalculator.R.id.tvTipAmount;
import static com.green.tipcalculator.R.id.tvTipDisplay;
import static com.green.tipcalculator.R.id.tvTotal;
import static com.green.tipcalculator.R.id.tvTotalDisplay;

public class MainActivity extends AppCompatActivity {

    private double billAmount;
    private int percent;
    private double tipAmount;
    private double total;

    private EditText editBillAmount;
    private TextView textPercent;
    private TextView textTotal;
    private TextView textTip;
    private TextView txtTipLabel;
    private TextView txtTotalLabel;
    private Button btPlus;
    private Button btMinus;
    private Button btCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editBillAmount = (EditText) findViewById(etBillAmount);
        textPercent = (TextView) findViewById(tvPercentDisplay);
        textTotal = (TextView) findViewById(tvTotalDisplay);
        textTip = (TextView) findViewById(tvTipDisplay);
        txtTipLabel = (TextView) findViewById(tvTipAmount);
        txtTotalLabel = (TextView) findViewById(tvTotal);

        percent = 15;
        textPercent.setText(percent+"");

        //animation stuff
        final Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);


        //Button Actions
        btPlus = (Button) findViewById(buttonPlus);
        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percent += 1;
                textPercent.setText(percent+"");
            }
        });

        btMinus = (Button) findViewById(buttonMinus);
        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percent -= 1;
                textPercent.setText(percent+"");
            }
        });

        btCalculate = (Button) findViewById(buttonCalculate);
        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editBillAmount.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter your bill amount to calculate tip.", Toast.LENGTH_LONG).show();
                }
                else {
                    //inititalize variables
                    billAmount = Double.parseDouble(editBillAmount.getText().toString());
                    tipAmount = ( (double) percent / 100) * billAmount;
                    total = billAmount + tipAmount;

                    textTip.setText("$" + String.format("%.2f", tipAmount));
                    textTotal.setText("$" + String.format("%.2f", total));

                    txtTipLabel.startAnimation(aniFade);
                    textTip.startAnimation(aniFade);
                    textTotal.startAnimation(aniFade);
                    txtTotalLabel.startAnimation(aniFade);

                    txtTipLabel.setVisibility(View.VISIBLE);
                    txtTotalLabel.setVisibility(View.VISIBLE);
                    textTip.setVisibility(View.VISIBLE);
                    textTotal.setVisibility(View.VISIBLE);
                }

            }
        });
        //-------------





    }
}
