package com.green.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.green.tipcalculator.R.id.buttonCalculate;
import static com.green.tipcalculator.R.id.buttonMinus;
import static com.green.tipcalculator.R.id.buttonPlus;
import static com.green.tipcalculator.R.id.etBillAmount;
import static com.green.tipcalculator.R.id.text;
import static com.green.tipcalculator.R.id.tvBillAmount;
import static com.green.tipcalculator.R.id.tvPercentDisplay;
import static com.green.tipcalculator.R.id.tvTipDisplay;
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

        percent = 15;
        textPercent.setText(percent+"");


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

                //inititalize variables
                billAmount = Double.parseDouble(editBillAmount.getText().toString());
                tipAmount = ( (double) percent / 100) * billAmount;
                total = billAmount + tipAmount;

                textTip.setText("$" + tipAmount);
                textTotal.setText("$" + total);
            }
        });
        //-------------





    }
}
