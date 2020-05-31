package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityMenu extends AppCompatActivity implements OnClickListener {

    private Button multiPlayerBtn;
    private Button singlePlayerBtn;
    private Button settingsBtn;
    private Button aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        multiPlayerBtn = findViewById(R.id.multi_player_btn);
        multiPlayerBtn.setOnClickListener(this);

        singlePlayerBtn = findViewById(R.id.single_player_btn);
        singlePlayerBtn.setOnClickListener(this);

        settingsBtn = findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(this);

        aboutBtn = findViewById(R.id.about_btn);
        aboutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multi_player_btn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.single_player_btn:
                Intent intent2 = new Intent(this, DifficultyActivity.class);
                startActivity(intent2);
                break;
            case R.id.settings_btn:
                Intent intent3 = new Intent(this, SettingsActivity.class);
                startActivity(intent3);
                break;
            case R.id.about_btn:
                Intent intent4 = new Intent(this, AboutActivity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
    }
}

