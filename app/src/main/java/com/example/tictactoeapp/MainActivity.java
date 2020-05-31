package com.example.tictactoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;
    private int draw;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private TextView textViewDraw;

    private Button resetGame, resetFields;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_player1);
        textViewPlayer2 = findViewById(R.id.text_view_player2);
        textViewDraw = findViewById(R.id.text_view_draw);
        resetFields = findViewById(R.id.button_reset_field);
        resetGame = findViewById(R.id.button_reset_game);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        if (isLandscape()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setTextSize(30);
                }
            }
        }

        resetFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetField();
            }
        });

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setTextColor(getResources().getColor(R.color.colorX));
            ((Button) v).setText("X");
            textViewPlayer1.setTextColor(getResources().getColor(R.color.colorO));
            textViewPlayer2.setTextColor(getResources().getColor(R.color.colorX));
        } else {
            ((Button) v).setTextColor(getResources().getColor(R.color.colorO));
            ((Button) v).setText("O");
            textViewPlayer1.setTextColor(getResources().getColor(R.color.colorX));
            textViewPlayer2.setTextColor(getResources().getColor(R.color.colorO));
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //Checking for horizontals
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //Checking for verticals
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //Checking for MainDiagonal
        for (int i = 0; i < 3; i++) {
            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")) {
                return true;
            }
        }

        //Checking for SideDiagonal
        for (int i = 0; i < 3; i++) {
            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")) {
                return true;
            }
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        textViewPlayer1.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewPlayer2.setTextColor(getResources().getColor(R.color.colorO));
        textViewDraw.setTextColor(getResources().getColor(R.color.colorO));
        Toast.makeText(this, "Player 1 won!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        textViewPlayer1.setTextColor(getResources().getColor(R.color.colorO));
        textViewPlayer2.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewDraw.setTextColor(getResources().getColor(R.color.colorO));
        Toast.makeText(this, "Player 2 won!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        draw++;
        textViewPlayer1.setTextColor(getResources().getColor(R.color.colorO));
        textViewPlayer2.setTextColor(getResources().getColor(R.color.colorO));
        textViewDraw.setTextColor(getResources().getColor(R.color.colorPrimary));
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
        textViewDraw.setText("Draws: " + draw);
    }

    private void resetGame() {
        textViewPlayer1.setText("Player 1: 0");
        textViewPlayer2.setText("Player 2: 0");
        textViewDraw.setText("Draws: 0");
        resetField();
    }

    private void resetField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        textViewPlayer1.setTextColor(getResources().getColor(R.color.colorX));
        textViewPlayer2.setTextColor(getResources().getColor(R.color.colorO));
        textViewDraw.setTextColor(getResources().getColor(R.color.colorO));
        roundCount = 0;
        player1Turn = true;
    }

    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }

        handler.postDelayed(new Runnable() {
            public void run() {
                //Delay in 2 sec
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setEnabled(true);
                    }
                }
                textViewPlayer1.setTextColor(getResources().getColor(R.color.colorX));
                textViewPlayer2.setTextColor(getResources().getColor(R.color.colorO));
                textViewDraw.setTextColor(getResources().getColor(R.color.colorO));
            }
        }, 2000);

        roundCount = 0;
        player1Turn = true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putInt("draw", draw);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        draw = savedInstanceState.getInt("draw");
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
