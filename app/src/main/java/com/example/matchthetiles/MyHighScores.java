package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

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

        //Round 1 Scores
        ArrayList scoresRound1 = intent.getIntegerArrayListExtra("scoresRound1");

        if(scoresRound1.size() > 0) {
            ((TextView) findViewById(R.id.score1Round1)).setText("   "+scoresRound1.get(0)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score1Round1)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score1Round1)).setText("  00  ");
        }
        if(scoresRound1.size() > 1) {
            ((TextView) findViewById(R.id.score2Round1)).setText("   "+scoresRound1.get(1)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score2Round1)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score2Round1)).setText("  00  ");
        }
        if(scoresRound1.size() > 2){
            ((TextView) findViewById(R.id.score3Round1)).setText("   " + scoresRound1.get(2)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score3Round1)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score3Round1)).setText("  00  ");
        }
        if(scoresRound1.size() > 3) {
            ((TextView) findViewById(R.id.score4Round1)).setText("   "+scoresRound1.get(3)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score4Round1)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score4Round1)).setText("  00  ");
        }
        if(scoresRound1.size() > 4) {
            ((TextView) findViewById(R.id.score5Round1)).setText("   "+scoresRound1.get(4)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score5Round1)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score5Round1)).setText("  00  ");
        }

        //Round 2 Scores
        ArrayList scoresRound2 = intent.getIntegerArrayListExtra("scoresRound2");

        if(scoresRound2.size() > 0) {
            ((TextView) findViewById(R.id.score1Round2)).setText("   "+scoresRound2.get(0)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score1Round2)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score1Round2)).setText("  00  ");
        }
        if(scoresRound2.size() > 1) {
            ((TextView) findViewById(R.id.score2Round2)).setText("   "+scoresRound2.get(1)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score2Round2)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score2Round2)).setText("  00  ");
        }
        if(scoresRound2.size() > 2) {
            ((TextView) findViewById(R.id.score3Round2)).setText("   "+scoresRound2.get(2)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score3Round2)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score3Round2)).setText("  00  ");
        }
        if(scoresRound2.size() > 3) {
            ((TextView) findViewById(R.id.score4Round2)).setText("   "+ scoresRound2.get(3)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score4Round2)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score4Round2)).setText("  00  ");
        }
        if(scoresRound2.size() > 4) {
            ((TextView) findViewById(R.id.score5Round2)).setText("   "+scoresRound2.get(4)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score5Round2)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score5Round2)).setText("  00  ");
        }

        //Round 3 Scores
        ArrayList scoresRound3 = intent.getIntegerArrayListExtra("scoresRound3");

        if(scoresRound3.size() > 0) {
            ((TextView) findViewById(R.id.score1Round3)).setText("   "+scoresRound3.get(0)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score1Round3)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score1Round3)).setText("  00  ");
        }
        if(scoresRound3.size() > 1) {
            ((TextView) findViewById(R.id.score2Round3)).setText("   "+scoresRound3.get(1)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score2Round3)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score2Round3)).setText("  00  ");

        }
        if(scoresRound3.size() > 2) {
            ((TextView) findViewById(R.id.score3Round3)).setText("   "+scoresRound3.get(2)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score3Round3)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score3Round3)).setText("  00  ");

        }
        if(scoresRound3.size() > 3) {
            ((TextView) findViewById(R.id.score4Round3)).setText("   "+scoresRound3.get(3)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score4Round3)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score4Round3)).setText("  00  ");

        }
        if(scoresRound3.size() > 4) {
            ((TextView) findViewById(R.id.score5Round3)).setText("   "+scoresRound3.get(4)+"   ");
        }
        else{
            ((TextView) findViewById(R.id.score5Round3)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.score5Round3)).setText("  00  ");

        }

        ConstraintLayout myHighScores = findViewById(R.id.myHighScoresLayout);
        switch(theme) {
            case "winter":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.winterbackground);

                for(int i = 0; i < myHighScores.getChildCount(); i++){
                    ((TextView)myHighScores.getChildAt(i)).setBackgroundColor(Color.BLUE);
                }
                break;
            case "fall":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.fallbackground);
                for(int i = 0; i < myHighScores.getChildCount(); i++){
                    ((TextView)myHighScores.getChildAt(i)).setBackgroundResource(R.color.ORANGE);
                    ((TextView)myHighScores.getChildAt(i)).setTextColor(Color.BLACK);
                }
                break;
            case "spring":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.springbackground);
                for(int i = 0; i < myHighScores.getChildCount(); i++){
                    ((TextView)myHighScores.getChildAt(i)).setBackgroundColor(Color.YELLOW);
                    ((TextView)myHighScores.getChildAt(i)).setTextColor(Color.BLACK);
                }
                break;
            case "summer":
                findViewById(R.id.myHighScoresLayout).setBackgroundResource(R.drawable.summerbackground);
                for(int i = 0; i < myHighScores.getChildCount(); i++){
                    ((TextView)myHighScores.getChildAt(i)).setBackgroundColor(Color.GREEN);
                    ((TextView)myHighScores.getChildAt(i)).setTextColor(Color.BLACK);
                }
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