package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v){
        Intent intent = new Intent(this, Round1.class);

        intent.putExtra("theme", theme);
        intent.putExtra("myBestTime", 15);
        intent.putExtra("globalBestTime", 9);
        startActivity(intent);
    }

    public void springTheme(View v){

        theme = "spring";
        findViewById(R.id.homeScreen).setBackgroundColor(Color.YELLOW);

    }

    public void fallTheme(View v){
        theme = "fall";
        findViewById(R.id.homeScreen).setBackgroundColor(Color.parseColor("#ffa500"));
    }

    public void winterTheme(View v){
        theme = "winter";
        findViewById(R.id.homeScreen).setBackgroundColor(Color.GREEN);

    }

    public void summerTheme(View v){

        theme = "summer";
        findViewById(R.id.homeScreen).setBackgroundColor(Color.BLUE);


    }
}