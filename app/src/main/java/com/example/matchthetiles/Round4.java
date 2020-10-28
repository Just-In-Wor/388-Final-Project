package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Round4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round4);
    }


    public void next(View v){

        Intent i = new Intent(this,Round5.class);
        startActivity(i);
    }
}