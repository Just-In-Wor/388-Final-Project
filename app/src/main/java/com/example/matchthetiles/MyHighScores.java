package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyHighScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_high_scores);
    }

    public void mainMenu(View v){

        //use this to go to next round
        Intent intent = new Intent(this,MainActivity.class);
//        intent.putExtra("theme", theme);
//        intent.putExtra("myBestTime", myBestTime);
//        intent.putExtra("globalBestTime", globalBestTime);
        startActivity(intent);
    }
}