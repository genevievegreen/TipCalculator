package com.green.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import org.w3c.dom.Text;

import static com.green.tipcalculator.R.id.buttonCalculate;
import static com.green.tipcalculator.R.id.buttonMinus;
import static com.green.tipcalculator.R.id.buttonPlus;
import static com.green.tipcalculator.R.id.buttonReset;
import static com.green.tipcalculator.R.id.cbSplit;
import static com.green.tipcalculator.R.id.etBillAmount;
import static com.green.tipcalculator.R.id.etSplitNum;
import static com.green.tipcalculator.R.id.tvPercentDisplay;
import static com.green.tipcalculator.R.id.tvSplitDisplay;
import static com.green.tipcalculator.R.id.tvTipAmount;
import static com.green.tipcalculator.R.id.tvTipDisplay;
import static com.green.tipcalculator.R.id.tvTotal;
import static com.green.tipcalculator.R.id.tvTotalDisplay;

public class MainActivity extends AppCompatActivity {

    private double billAmount;
    private int percent;
    private double tipAmount;
    private double total;
    private int splitNum;
    private double splitAmount;

    private EditText editBillAmount;
    private TextView textPercent;
    private TextView textTotal;
    private TextView textTip;
    private TextView txtTipLabel;
    private TextView txtTotalLabel;
    private Button btPlus;
    private Button btMinus;
    private Button btCalculate;
    private CheckBox chSplit;
    private EditText editSplitNum;
    private TextView textSplit;
    private Button btReset;

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
        editSplitNum = (EditText) findViewById(etSplitNum);
        textSplit = (TextView) findViewById(tvSplitDisplay);
        chSplit = (CheckBox) findViewById(cbSplit);

        splitNum = 1;
        percent = 15;
        textPercent.setText(percent+"");

        //animation stuff
        final Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        final Animation aniFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        //chip checked
        chSplit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chSplit.isChecked()) {
                    editSplitNum.startAnimation(aniFade);
                    editSplitNum.setVisibility(View.VISIBLE);
                } else {
                    editSplitNum.startAnimation(aniFadeOut);
                    editSplitNum.setVisibility(View.INVISIBLE);
                }
            }
        });


        //Button Actions
        btReset = (Button) findViewById(buttonReset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtTipLabel.getVisibility()==View.VISIBLE) {
                    txtTipLabel.setAnimation(aniFadeOut);
                    txtTotalLabel.setAnimation(aniFadeOut);
                    textTip.setAnimation(aniFadeOut);
                    textTotal.setAnimation(aniFadeOut);

                    if (textSplit.getVisibility() == View.VISIBLE) {
                        textSplit.setAnimation(aniFadeOut);
                        textSplit.setVisibility(View.INVISIBLE);
                    }

                    txtTipLabel.setVisibility(View.INVISIBLE);
                    txtTotalLabel.setVisibility(View.INVISIBLE);
                    textTip.setVisibility(View.INVISIBLE);
                    textTotal.setVisibility(View.INVISIBLE);

                    editBillAmount.setText("");
                    editSplitNum.setText("");

                }
            }
        });

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

                    textSplit.setVisibility(View.INVISIBLE);

                    if (chSplit.isChecked()) {
                        if (editSplitNum.getText().toString()=="" || TextUtils.isEmpty(editSplitNum.getText())) {
                            splitNum = 1;

                            splitAmount = total / (double) splitNum;
                        }
                        else {
                            splitNum = Integer.parseInt(editSplitNum.getText().toString());

                            splitAmount = total / (double) splitNum;

                            textSplit.setText("Each divided total is: $" + String.format("%.2f", splitAmount));
                            textSplit.startAnimation(aniFade);
                            textSplit.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });
        //-------------

    }
}
