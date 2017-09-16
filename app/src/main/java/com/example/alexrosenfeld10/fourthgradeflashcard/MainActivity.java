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
    private TextView txtDividend;
    private TextView txtDivisor;

    private int numCorrect = 0;
    private int numQuestions = 0;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupQuestion();
    }

    /* View editing helper functions */

    public void resetQuestion(View v){

        checkAnswer();

        numQuestions++;

        if (numQuestions == 10) {
            String message = "You got " + numCorrect + " correct!";
            Toast toast = Toast.makeText(getBaseContext(), message , Toast.LENGTH_LONG);
            toast.show();
            numQuestions = 0; // let the user keep going
            numCorrect = 0; // reset correct count
        }

        setupQuestion();
    }

    private void setupQuestion() {

        /* Find view references */

        edtQuotient = (EditText) findViewById(R.id.edtQuotient);
        txtDividend = (TextView) findViewById(R.id.txtDividend);
        txtDivisor = (TextView) findViewById(R.id.txtDivisor);

        int newDivisor = random.nextInt(50 - 1);
        txtDivisor.setText(Integer.toString(newDivisor));
        int newDividend = generateDividendFor(newDivisor);
        txtDividend.setText(Integer.toString(newDividend));
        edtQuotient.setText("");
    }

    /* Mathematical helper functions */

    private int generateDividendFor(int divisor) {
        int dividend = divisor;

        // Heuristic approach for getting a new problem
        // If we can't find anything within 500 tries, return dividend = divisor
        // this way, quotient = 1
        for (int i = 0; i < 500; i++) {
            int newDividend = divisor * (random.nextInt(50 - 1) + 1);
            if (newDividend <= 100){
                return newDividend;
            }
        }

        return dividend;
    }

    private void checkAnswer() {

        int divisor = Integer.parseInt(txtDivisor.getText().toString());
        int dividend = Integer.parseInt(txtDividend.getText().toString());
        int correctAnswer = dividend / divisor;

        String answer_string = edtQuotient.getText().toString();
        int answer = Integer.parseInt(answer_string);

        if (answer == correctAnswer) {
            numCorrect++;
        }
    }
}