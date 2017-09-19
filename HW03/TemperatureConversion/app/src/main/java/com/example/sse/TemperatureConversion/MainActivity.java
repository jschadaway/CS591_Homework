package com.example.sse.TemperatureConversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sse.lect2_activitylifecycle_logging_savingstate.R;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "LECT2_FLAG";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;

    private EditText txtC;
    private EditText txtF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onCreate State Transition");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//Let's find our view references

        txtC = (EditText) findViewById(R.id.txtC);
        txtF = (EditText) findViewById(R.id.txtF);

        txtC.setSelectAllOnFocus(true);
        txtF.setSelectAllOnFocus(true);

        txtC.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == txtC) {
                    String C = txtC.getText().toString();
                    String F = convertTemp(C, false);
                    if (F.equals("err")) {
                        txtF.setText("");
                    } else {
                        txtF.setText(F);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        txtF.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == txtF) {
                    String F = txtF.getText().toString();
                    String C = convertTemp(F, true);
                    if (C.equals("err")) {
                        txtC.setText("");
                    } else {
                        txtC.setText(C);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });


    }

    //Useful Notes:
        // ctrl-O is a shortcut to override base methods
        // Alt-Ins is a shortcut to overriding base methods and more.

    @Override
    protected void onPause() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onPause State Transition");
        super.onPause();
    }


    @Override
    protected void onStart() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onStart State Transition");
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onSaveInstanceState State Transition");
        Log.i(MyFlag, "Bundling State of our views before they get destroyed");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        Log.i(MyFlag, "Retrieving our saved state from before... ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        super.onResume();
    }


//Handy Helpers...
    private String convertTemp(String inTemp, boolean toCelsius) {
        Double degreeIn, degreeOut;
        try {
            degreeIn = Double.parseDouble(inTemp);
        } catch (NumberFormatException e) {
            return "err";
        }
        if (toCelsius) {
            degreeOut = (degreeIn - 32) / 1.8;
        } else {
            degreeOut = degreeIn * 1.8 + 32;
        }
        return String.format("%.02f", degreeOut);
    }

    public String intToStr(Integer i)
    {
        return i.toString();
    }

    public int strToInt(String S)
    {
       return Integer.parseInt(S);
    }


}

