package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Round2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round2);
    }



    public void next(View v){

        Intent i = new Intent(this,Round3.class);
        startActivity(i);
    }
}