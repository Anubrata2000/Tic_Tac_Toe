package com.example.tictactoe;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons;
    private Button resetButton;
    private boolean isPlayerOneTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[9];
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (button.getText().toString().isEmpty()) {
            if (isPlayerOneTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            isPlayerOneTurn = !isPlayerOneTurn;
            checkForWin();
        }
    }

    private void checkForWin() {
        String[] board = new String[9];
        for (int i = 0; i < buttons.length; i++) {
            board[i] = buttons[i].getText().toString();
        }

        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        for (int[] combination : winningCombinations) {
            if (board[combination[0]].equals(board[combination[1]]) &&
                    board[combination[1]].equals(board[combination[2]]) &&
                    !board[combination[0]].isEmpty()) {
                // We have a winner
                String winner = board[combination[0]];
                Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();
                resetGame();
                return;
            }
        }
    }

    private void resetGame() {
        for (Button button : buttons) {
            button.setText("");
        }
        isPlayerOneTurn = true;
    }
}
