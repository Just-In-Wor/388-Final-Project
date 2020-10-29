package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyHighScores extends AppCompatActivity {

    private int score;
    private String theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_high_scores);

        Intent intent = getIntent();
        score = 0;
        this.theme = intent.getStringExtra("theme");
        String username = intent.getStringExtra("username");
        ((TextView)findViewById(R.id.username)).setText(username);


        switch(theme) {
            case "winter":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.winterbackground);
                break;
            case "fall":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.fallbackground);
                break;
            case "spring":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.springbackground);
                break;
            case "summer":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.summerbackground);
                break;
        }
    }

    public void mainMenu(View v){

        //use this to go to next round
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("theme", theme);
//        intent.putExtra("myBestTime", myBestTime);
//        intent.putExtra("globalBestTime", globalBestTime);
        startActivity(intent);
    }
}