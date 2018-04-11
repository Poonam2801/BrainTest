package com.example.poonamgupta2801.braintest;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton, playAgain, answer1, answer2, answer3, answer4;
    TextView pointsText, timerText, answerCheck, sumEditText;
    ArrayList<Integer> answers = new ArrayList<> ();
    int locationOfCorrectAnswer;
    CountDownTimer countDownTimer;
    int score=0;
    int numberOfQuestions=0;
    String performance="";
    RelativeLayout goLayout;
    RelativeLayout mainLayout;
    GridLayout gridLayout;

    public void generateQuestion(){

        Random random = new Random ();

        int a = random.nextInt ( 21 );
        int b = random.nextInt ( 21 );

        sumEditText.setText ( Integer.toString ( a ) + "+" + Integer.toString ( b ) );

        locationOfCorrectAnswer = random.nextInt ( 3 );

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {
                answers.add ( a + b );
            } else {
                incorrectAnswer = random.nextInt ( 40 );
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt ( 40 );
                }

                answers.add ( incorrectAnswer );
            }


        }
        answer1.setText ( Integer.toString ( answers.get ( 0 ) ) );
        answer2.setText ( Integer.toString ( answers.get ( 1 ) ) );
        answer3.setText ( Integer.toString ( answers.get ( 2 ) ) );
        answer4.setText ( Integer.toString ( answers.get ( 3 ) ) );


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        goLayout = (RelativeLayout) findViewById ( R.id.goLayout );
        mainLayout = (RelativeLayout) findViewById ( R.id.mainLayout );
        gridLayout=(GridLayout)findViewById ( R.id.gridLayout );

        sumEditText = (TextView) findViewById ( R.id.sumEditText );
        timerText=(TextView) findViewById ( R.id.timerText );
        answerCheck=(TextView) findViewById ( R.id.answerCheck );
        pointsText=(TextView) findViewById ( R.id.pointText );

        goButton = (Button) findViewById ( R.id.goButton );
        playAgain=(Button) findViewById ( R.id.playAgain );
        answer1 = (Button) findViewById ( R.id.answer1 );
        answer2 = (Button) findViewById ( R.id.answer2 );
        answer3 = (Button) findViewById ( R.id.answer3 );
        answer4 = (Button) findViewById ( R.id.answer4 );

        answer1.setTag (0);
        answer2.setTag (1);
        answer3.setTag (2);
        answer4.setTag (3);



    }

    public void playAgain(View view) {


            score = 0;
            numberOfQuestions = 0;


            timerText.setText("30s");
            pointsText.setText("0/0");
            answerCheck.setVisibility ( View.INVISIBLE);
            generateQuestion ();
            gridLayout.setVisibility ( GridLayout.VISIBLE );
            resetTimer ();


    }

    public void start(View view) {

        goLayout.setVisibility ( RelativeLayout.INVISIBLE );
        mainLayout.setVisibility ( RelativeLayout.VISIBLE );
        playAgain.setVisibility ( View.INVISIBLE );
        answerCheck.setVisibility ( View.INVISIBLE);
        generateQuestion ();
        resetTimer ();



    }

    public void chooseAnswer(View view) {

        answerCheck.setVisibility ( View.VISIBLE);

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;

            answerCheck.setText("Correct!");

        } else {

            answerCheck.setText("Wrong!");
        }

        numberOfQuestions++;
        performance=Integer.toString(score) + "/" + Integer.toString(numberOfQuestions);
        pointsText.setText(performance);
        generateQuestion();


    }

    public void resetTimer(){

        countDownTimer = new CountDownTimer ( 30100, 1000 ) {

            public void onTick(long millisUntilFinished) {
                timerText.setText ( String.valueOf(millisUntilFinished / 1000) + "s" );

            }

            public void onFinish() {
                timerText.setText ( "00s" );
                playAgain.setVisibility ( View.VISIBLE );
                playAgain.setText ( "PlAY AGAIN" );
                pointsText.setText ( performance );
                answerCheck.setVisibility ( View.VISIBLE );
                answerCheck.setText ( "Score: "+performance );
                gridLayout.setVisibility ( GridLayout.INVISIBLE );
            }
        }.start ();

    }


}


