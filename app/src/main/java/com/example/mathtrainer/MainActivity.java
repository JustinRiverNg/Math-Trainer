package com.example.mathtrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView problemTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    boolean gameActive = false;

    public void playAgain(View view) {
        score = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + " pts");
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        gameActive = true;

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Times Up!");
                playAgainButton.setVisibility(View.VISIBLE);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.service_bell);
                mp.start();
                gameActive = false;

            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if (gameActive) {
            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                score++;
            } else {
                resultTextView.setText("Wrong!");
                score--;
            }
            scoreTextView.setText(Integer.toString(score) + " pts");
            newQuestion();
        }
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView)); // The view parameter doesn't matter. We just need it to call the method.
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion() {
        Random rand = new Random();
        int problemType = rand.nextInt(3);

        /** Addition Problem. */
        if (problemType == 0) {
            int a = rand.nextInt(100);
            int b = rand.nextInt(100);
            problemTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

            /** Generate a random location for the answer. */
            locationOfCorrectAnswer = rand.nextInt(4);

            /** Clear the array list before filling it. */
            answers.clear();

            /** Add answers into the selections. */
            for(int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answers.add(a + b);
                }
                else {
                    /** If we happen to randomly generate the correct answer, randomize it again. */
                    int wrongAnswer = rand.nextInt(199);
                    while (wrongAnswer == a + b) {
                        wrongAnswer = rand.nextInt(199);
                    }
                    answers.add(wrongAnswer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }
        /** Subtraction Problem. */
        else if (problemType == 1) {
            int a = rand.nextInt(100);
            int b = rand.nextInt(100);
            problemTextView.setText(Integer.toString(a) + " - " + Integer.toString(b));

            /** Generate a random location for the answer. */
            locationOfCorrectAnswer = rand.nextInt(4);

            /** Clear the array list before filling it. */
            answers.clear();

            /** Add answers into the selections. */
            for(int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answers.add(a - b);
                }
                else {
                    /** If we happen to randomly generate the correct answer, randomize it again. */
                    int wrongAnswer = rand.nextInt(199 + 199) - 199;
                    while (wrongAnswer == a - b) {
                        wrongAnswer = rand.nextInt(199 + 199) - 199;
                    }
                    answers.add(wrongAnswer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }
        /** Multiplication Problem. */
        else {
            int a = rand.nextInt(51);
            int b = rand.nextInt(10);
            problemTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));

            /** Generate a random location for the answer. */
            locationOfCorrectAnswer = rand.nextInt(4);

            /** Clear the array list before filling it. */
            answers.clear();

            /** Add answers into the selections. */
            for(int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answers.add(a * b);
                }
                else {
                    /** If we happen to randomly generate the correct answer, randomize it again. */
                    int wrongAnswer = rand.nextInt(450);
                    while (wrongAnswer == a * b) {
                        wrongAnswer = rand.nextInt(450);
                    }
                    answers.add(wrongAnswer);
                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        problemTextView = findViewById(R.id.problemTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        startButton = findViewById(R.id.startButton);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
