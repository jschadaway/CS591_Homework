package com.example.alexrosenfeld10.fourthgradeflashcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText edtQuotient;
    private TextView txtYourAnswer;
    private Button btnSubmit;
    private TextView txtDividend;
    private TextView txtDivisor;
    private TextView txtDivisionSign;

    private int numCorrect = 0;
    private int numQuestions = 0;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Find view references */

        edtQuotient = (EditText) findViewById(R.id.edtQuotient);
        txtYourAnswer = (TextView) findViewById(R.id.txtYourAnswer);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtDividend = (TextView) findViewById(R.id.txtDividend);
        txtDivisor = (TextView) findViewById(R.id.txtDivisor);
        txtDivisionSign = (TextView) findViewById(R.id.txtDivisionSign);

        /* Set click listeners */

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numQuestions++;
                resetQuestion();
            }
        });

        setupQuestion();
    }

    /* View editing helper functions */

    private void resetQuestion(){

        checkAnswer();

        if (numQuestions == 10) {
            Toast.makeText(getBaseContext(), "You got " + numCorrect + " correct!", Toast.LENGTH_LONG);
            return;
        }
        setupQuestion();
    }

    private void setupQuestion() {
        int newDivisor = random.nextInt(100 - 1);
        txtDivisor.setText(Integer.toString(newDivisor));
        int newDividend = generateDividendFor(newDivisor);
        txtDividend.setText(Integer.toString(newDividend));
        edtQuotient.setText("");
    }

    /* Mathematical helper functions */

    private int generateDividendFor(int divisor) {
        int dividend = 1;

        // Heuristic approach for getting a new problem. If we can't find anything within 20 tries, use 1.
        for (int i = 0; i < 50; i++) {
            int newDividend = divisor * random.nextInt(100 - 1) + 1;
            if (newDividend <= 100){
                return newDividend;
            }
        }

        return dividend;
    }

    private void checkAnswer() {

        int prevDivisor = Integer.parseInt(txtDivisor.getText().toString());
        int prevDividend = Integer.parseInt(txtDividend.getText().toString());
        int correctAnswer = prevDividend / prevDivisor;

        String answer = edtQuotient.getText().toString();
        int actualAnswer = Integer.parseInt(answer);

        if (actualAnswer == correctAnswer) {
            numCorrect++;
        }
    }
}
