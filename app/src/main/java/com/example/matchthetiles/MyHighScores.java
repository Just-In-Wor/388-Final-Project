package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

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


        switch(theme) {
            case "winter":
                findViewById(R.id.myHighScoresLayout).setBackgroundColor(Color.GREEN);
                break;
            case "fall":
                findViewById(R.id.myHighScoresLayout).setBackgroundColor(Color.parseColor("#ffa500"));
                break;
            case "spring":
                findViewById(R.id.myHighScoresLayout).setBackgroundColor(Color.YELLOW);
                break;
            case "summer":
                findViewById(R.id.myHighScoresLayout).setBackgroundColor(Color.BLUE);
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