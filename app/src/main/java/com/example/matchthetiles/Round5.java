package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class Round5 extends AppCompatActivity {


    //keeps track of how many are flipped up currently
    private int flippedUp;

    //id of the two tiles flipped up to know if they match
    //1 for cy, 2 for cycircle and 3 for statelogo
    private int firstTileHidden;

    //Resource ids of the tiles flipped
    private ImageView firstTileResource;

    private int themeid;

    Handler handler = new Handler();

    int time;

    int score;

    int myBestTime;

    int globalBestTime;
    String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round5);

        Intent intent = getIntent();
        score = 0;
        this.theme = intent.getStringExtra("theme");

        switch(theme) {
            case "winter":
                changeTheme(R.drawable.snowflaketile);
                findViewById(R.id.round5Layout).setBackgroundColor(Color.GREEN);
                break;
            case "fall":
                changeTheme(R.drawable.leaf);
                findViewById(R.id.round5Layout).setBackgroundColor(Color.parseColor("#ffa500"));
                break;
            case "spring":
                changeTheme(R.drawable.spring);
                findViewById(R.id.round5Layout).setBackgroundColor(Color.YELLOW);
                break;
            case "summer":
                changeTheme(R.drawable.summer);
                findViewById(R.id.round5Layout).setBackgroundColor(Color.BLUE);
                break;
        }
    }


    Runnable timer = new Runnable() {
        @Override
        public void run() {

//            handler.postDelayed(timer, 1000);
//            TextView timeView = findViewById(R.id.textView);
//            time++;
//            timeView.setText("Time:"+time);

        }

    };


    public void changeTheme(int imageResource){
//        themeid = imageResource;
//        iv1.setImageResource(imageResource);
//        iv2.setImageResource(imageResource);
//        iv3.setImageResource(imageResource);
//        iv4.setImageResource(imageResource);
//        iv5.setImageResource(imageResource);
//        iv6.setImageResource(imageResource);

    }


    public void mainMenu(View v){

        //use this to go to next round
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("theme", theme);
        intent.putExtra("myBestTime", myBestTime);
        intent.putExtra("globalBestTime", globalBestTime);
        startActivity(intent);
    }
}