package com.example.miras.calculator;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Double num1 = 0.0;
    Double num2 = 0.0;
    Double res;

    Boolean turn = false;
    Boolean last = true;
    Boolean equal = true;

    enum Operation {Add, Sub, Mul, Div};
    Operation operation;

    Button number1, number2, number3;
    Button number4, number5, number6;
    Button number7, number8, number9;
    Button number0;

    Button operationAdd;
    Button operationSub;
    Button operationMul;
    Button operationDiv;

    Button clear;
    Button result;

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        number1 = (Button)findViewById(R.id.bnClickNumber1);
        number2 = (Button)findViewById(R.id.bnClickNumber2);
        number3 = (Button)findViewById(R.id.bnClickNumber3);
        number4 = (Button)findViewById(R.id.bnClickNumber4);
        number5 = (Button)findViewById(R.id.bnClickNumber5);
        number6 = (Button)findViewById(R.id.bnClickNumber6);
        number7 = (Button)findViewById(R.id.bnClickNumber7);
        number8 = (Button)findViewById(R.id.bnClickNumber8);
        number9 = (Button)findViewById(R.id.bnClickNumber9);
        number0 = (Button)findViewById(R.id.bnClickNumber0);

        operationAdd = (Button)findViewById(R.id.bnClickAddition);
        operationSub = (Button)findViewById(R.id.bnClickSubtraction);
        operationMul = (Button)findViewById(R.id.bnClickMultiplication);
        operationDiv = (Button)findViewById(R.id.bnClickDivision);

        result = (Button)findViewById(R.id.bnClickResult);
        clear = (Button) findViewById(R.id.bnClickClear);

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "1");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "2");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "3");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "4");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "5");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "6");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "7");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "8");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "9");
                turn = true;
                last = false;
                equal = false;
            }
        });

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == false) textView.setText("");
                textView.setText(textView.getText() + "0");
                turn = true;
                last = false;
                equal = false;
            }
        });

        operationAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (last == true) return;
                num1 = Double.valueOf(textView.getText().toString());
                textView.setText("");
                operation = Operation.Add;
                last = true;
                equal = false;
            }
        });

        operationSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (last == true) return;
                num1 = Double.valueOf(textView.getText().toString());
                textView.setText("");
                operation = Operation.Sub;
                last = true;
                equal = false;
            }
        });

        operationMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (last == true) return;
                num1 = Double.valueOf(textView.getText().toString());
                textView.setText("");
                operation = Operation.Mul;
                last = true;
                equal = false;
            }
        });

        operationDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (last == true) return;
                num1 = Double.valueOf(textView.getText().toString());
                textView.setText("");
                operation = Operation.Div;
                last = true;
                equal = false;
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equal == true) return;
                if (textView.getText() == "") return;

                num2 = Double.valueOf(textView.getText().toString());


                if (operation == Operation.Add) res = num1 + num2;
                if (operation == Operation.Sub) res = num1 - num2;
                if (operation == Operation.Mul) res = num1 * num2;
                if (operation == Operation.Div) res = num1 / num2;
                textView.setText(res.toString());


                turn = false;
                equal = true;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });
    }
}
