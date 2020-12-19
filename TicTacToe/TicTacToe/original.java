package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //X and O strings
    String xValue = "X";
    String oValue = "O";

    //counter for what round of the game it is
    int gameRound = 1;

    //buttons as class level variables
    //set initial value to null
    Button firstButton, secondButton, thirdButton, fourthButton, fifthButton, sixthButton,
            seventhButton, eighthButton, ninthButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set on click listener to buttons
        View firstButton = findViewById(R.id.button1);
        firstButton.setOnClickListener(this);

        View secondButton = findViewById(R.id.button2);
        secondButton.setOnClickListener(this);

        View thirdButton = findViewById(R.id.button3);
        thirdButton.setOnClickListener(this);

        View fourthButton = findViewById(R.id.button4);
        fourthButton.setOnClickListener(this);

        View fifthButton = findViewById(R.id.button5);
        fifthButton.setOnClickListener(this);

        View sixthButton = findViewById(R.id.button6);
        sixthButton.setOnClickListener(this);

        View seventhButton = findViewById(R.id.button7);
        seventhButton.setOnClickListener(this);

        View eighthButton = findViewById(R.id.button8);
        eighthButton.setOnClickListener(this);

        View ninthButton = findViewById(R.id.button9);
        ninthButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view){

        firstButton = findViewById(R.id.button1);
        secondButton = findViewById(R.id.button2);
        thirdButton = findViewById(R.id.button3);
        fourthButton = findViewById(R.id.button4);
        fifthButton = findViewById(R.id.button5);
        sixthButton = findViewById(R.id.button6);
        seventhButton = findViewById(R.id.button7);
        eighthButton = findViewById(R.id.button8);
        ninthButton = findViewById(R.id.button9);

        //current player is string value either X or O
        //if game round is /2 then it is O, otherwise it is X
        String currentPlayer = gameRound % 2 == 0 ? oValue: xValue;//ternary, :-)

        //cases for all of the button clicks
        switch(view.getId()){
            case R.id.button1:
                firstButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button2:
                secondButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button3:
                thirdButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button4:
                fourthButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button5:
                fifthButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button6:
                sixthButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button7:
                seventhButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button8:
                eighthButton.setText(currentPlayer);
                gameRound++;
                break;

            case R.id.button9:
                ninthButton.setText(currentPlayer);
                gameRound++;
                break;

            default:
                break;
        }//end SWITCH

        //create new game of tic tac toe
        Game newGame = new Game();

        newGame.winCheck();



    }//end onClick() method

}//end MainActivity class