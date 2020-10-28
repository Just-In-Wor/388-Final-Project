package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }

    public void fallTheme(View v){
        theme = "fall";
    }

    public void winterTheme(View v){
        theme = "winter";

    }

    public void summerTheme(View v){

        theme = "summer";

    }
}