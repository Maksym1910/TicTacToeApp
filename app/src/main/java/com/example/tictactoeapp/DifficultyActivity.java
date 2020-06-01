package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultyActivity extends AppCompatActivity implements View.OnClickListener {

    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        startBtn = findViewById(R.id.start_game_btn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_game_btn:
                Intent intent2 = new Intent(this, ActivitySingle.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}