package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class GlobalHighScores extends AppCompatActivity {

    private int score;
    private String theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_high_scores);

        Intent intent = getIntent();
        score = 0;
        this.theme = intent.getStringExtra("theme");


        switch(theme) {
            case "winter":
                findViewById(R.id.globalHighScoresLayout).setBackgroundResource(R.drawable.winterbackground);
                break;
            case "fall":
                findViewById(R.id.globalHighScoresLayout).setBackgroundResource(R.drawable.fallbackground);
                break;
            case "spring":
                findViewById(R.id.globalHighScoresLayout).setBackgroundResource(R.drawable.springbackground);
                break;
            case "summer":
                findViewById(R.id.globalHighScoresLayout).setBackgroundResource(R.drawable.summerbackground);
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