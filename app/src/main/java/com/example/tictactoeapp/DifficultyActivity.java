package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class DifficultyActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup;
    Button startBtn;
    LinearLayout back;
    int bot = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        startBtn = findViewById(R.id.start_game_btn);
        radioGroup = findViewById(R.id.radioGroup);
        back = findViewById(R.id.back);
        back.setBackgroundColor(getResources().getColor(R.color.colorAppBackgroundGreen));
        startBtn.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_btn_easy:
                        bot = 1;
                        back.setBackgroundColor(getResources().getColor(R.color.colorAppBackgroundGreen));
                        break;
                    case R.id.radio_btn_medium:
                        bot = 2;
                        back.setBackgroundColor(getResources().getColor(R.color.colorAppBackgroundYellow));
                        break;
                    case R.id.radio_btn_ai:
                        bot = 3;
                        back.setBackgroundColor(getResources().getColor(R.color.colorAppBackgroundRed));
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_game_btn:
                Intent intent = new Intent(this, ActivitySingle.class);
                intent.putExtra("whichBot", bot);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}