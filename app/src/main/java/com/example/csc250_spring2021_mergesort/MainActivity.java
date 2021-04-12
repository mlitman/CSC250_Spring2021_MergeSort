package com.example.csc250_spring2021_mergesort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText inputET;
    private TextView answerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inputET = this.findViewById(R.id.inputET);
        this.answerTV = this.findViewById(R.id.answerTV);
    }

    private int factorialIter(int n)
    {
        int runningTotal = n;
        for(int i = n - 1; i > 0; i--)
        {
            runningTotal *= i;
        }
        return runningTotal;
    }

    private int factorialRec(int n)
    {
        if(n == 1) return 1;
        return n * factorialRec(n-1);
    }

    private int factorialRec2(int n)
    {
        //Inline if statement
        // boolean_exp?true_exp;false_exp;
        return n == 1?1:n * factorialRec2(n-1);
    }

    public void onFactorialButtonClicked(View v)
    {
        String currValue = this.inputET.getText().toString();
        int currValueAsInt = Integer.parseInt(currValue);
        this.answerTV.setText("" + this.factorialRec2(currValueAsInt));
    }


}