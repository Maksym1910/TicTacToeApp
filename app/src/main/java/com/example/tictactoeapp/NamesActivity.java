package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NamesActivity extends AppCompatActivity implements View.OnClickListener {

    Button startGameBtn;
    EditText player1Name, player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        player1Name = findViewById(R.id.edit_text_player1_name);
        player2Name = findViewById(R.id.edit_text_player2_name);
        startGameBtn = findViewById(R.id.start_game_btn);
        startGameBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_game_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("player1Name", player1Name.getText().toString());
            intent.putExtra("player2Name", player2Name.getText().toString());
            startActivity(intent);
        }
    }
}