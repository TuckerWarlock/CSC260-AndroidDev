package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //array of buttons
    public Button[][] buttons = new Button[3][3];

    //check if it is first player turn
    private boolean playerXturn = true;
    //keep track of rounds
    private int roundCount = 0;
    private boolean playable = false;

    //X and O strings
    String xValue = "X";
    String oValue = "O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set on click listener for reset button
        View resetButton = findViewById(R.id.Reset);
        resetButton.setOnClickListener(this);
        //set on click listener for play button
        View playButton = findViewById(R.id.Play);
        playButton.setOnClickListener(this);

        //assign ids to buttons and enable on click listeners
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                //loop through all button ids created
                String buttonId = "button_" + i + j;
                int resourceId = getResources().getIdentifier(buttonId, "id", getPackageName());
                //dynamically find view by ID for all the buttons in the array
                buttons[i][j] = findViewById(resourceId);
                //dynamically set onClickListener for all the buttons in the array
                buttons[i][j].setOnClickListener(this);
            }//end j loop
        }//end i loop

        //set buttons to not be clickable
        playLock();

    }//end onCreate method

    @Override
    public void onClick(View view){
        //enable buttons to be clicked so game can start
        if (view.getId() == R.id.Play){
            playUnLock();
        }//end Play button

        if (view.getId() == R.id.Reset){
            //set board back to blank tiles
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    //set all buttons to empty string
                    buttons[i][j].setText("");
                }//end j loop
            }//end i loop

            //reset the round count
            roundCount = 0;
            //set player back to X
            playerXturn = true;

            //allow game to be played again
            playUnLock();
        }//end Reset button

        //if button clicked is not empty string
        if(!((Button) view).getText().toString().equals("")){
            return;
        }//end IF

        //set X and O values based on turns
        if(playerXturn){
            ((Button) view).setText(xValue);
            ((Button) view).setTextColor(Color.BLUE);
        } else {
            ((Button) view).setText(oValue);
            ((Button) view).setTextColor(Color.RED);
        }
        //increment rounds of game
        roundCount++;

        //check if there is a winner
        if(winner()){
            //if checkWin() is true on player 1 turn, they win
            if(playerXturn){
                playerXWins();
            } else {
                //else checkWin() is true on player 2 turn, they win
                playerOWins();
            }
            //if all 9 rounds are played and neither player wins, it is a tie game
        } else if (roundCount == 9){
            tieGame();
        } else {
            //if there is no winner and no draw
            //determine next players turn
            playerXturn = !playerXturn;
        }
    }//end onClick() method

    //method to check if there are three in a row, column, or diagonal
    public boolean winner(){
        String[][] textPlay = new String[3][3];

        //set the text value of button to the textPlay string array
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                textPlay[i][j] = buttons[i][j].getText().toString();
            }//end j loop
        }//end i loop

        //check rows for equal string values
        for(int i = 0; i < 3; i++){
            //compare first two cells
            if(textPlay[i][0].equals(textPlay[i][1])
                    //compare second two cells
                    && textPlay[i][0].equals(textPlay[i][2])
                    //make sure we are not comparing empty strings
                    && !textPlay[i][0].equals("")){
                return true;
            }//end IF
        }//end FOR loop

        //check columns for equal string values
        for(int i = 0; i < 3; i++){
            //compare first two cells
            if(textPlay[0][i].equals(textPlay[1][i])
                    //compare second two cells
                    && textPlay[0][i].equals(textPlay[2][i])
                    //make sure we are not comparing empty strings
                    && !textPlay[0][i].equals("")){
                return true;
            }//end IF
        }//end FOR loop

        //check diagonal (top left to bottom right)
        if(textPlay[0][0].equals(textPlay[1][1])
                //compare second two cells
                && textPlay[0][0].equals(textPlay[2][2])
                //make sure we are not comparing empty strings
                && !textPlay[0][0].equals("")){
            return true;
        }//end IF

        //check diagonal (top right to bottom left)
        if(textPlay[0][2].equals(textPlay[1][1])
                //compare second two cells
                && textPlay[0][2].equals(textPlay[2][0])
                //make sure we are not comparing empty strings
                && !textPlay[0][2].equals("")){
            return true;
        }//end IF

        //game is not over
        return false;
    }//end checkWin() method

    //let the button be clicked
    private void playLock(){
        playable = false;
        if(!playable){
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //set all buttons to empty string
                    buttons[i][j].setEnabled(false);
                }// end j loop
            }//end i loop
        }//end IF playable
    }//end playLock() method

    //don't let the buttons be clicked
    private void playUnLock(){
        playable = true;
        if(playable){
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //set all buttons to empty string
                    buttons[i][j].setEnabled(true);
                }// end j loop
            }//end i loop
        }//end IF playable
    }//end playUnLock() method


    //show message when X wins and lock buttons
    private void playerXWins(){
        Toast.makeText(this, "X wins!", Toast.LENGTH_SHORT).show();
        playLock();
    }

    //show message when O wins and lock buttons
    private void playerOWins(){
        Toast.makeText(this, "O wins!", Toast.LENGTH_SHORT).show();
        playLock();
    }

    //show message when there is a tie and lock buttons
    private void tieGame(){
        Toast.makeText(this, "It's a tie!", Toast.LENGTH_SHORT).show();
        playLock();
    }


}//end MainActivity class