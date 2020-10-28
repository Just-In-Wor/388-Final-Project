package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Round1 extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;

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



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        handler.postDelayed(timer, 1000);
        flippedUp = 0;
        Intent intent = getIntent();
        score = 0;

        String theme = intent.getStringExtra("theme");
        iv1 = findViewById(R.id.imageView);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);

        switch(theme) {
            case "winter":
                changeTheme(R.drawable.snowflaketile);
            break;
            case "fall":
                changeTheme(R.drawable.leaf);
                break;
            case "spring":
                changeTheme(R.drawable.spring);
                break;
            case "summer":
                changeTheme(R.drawable.summer);
                break;
        }

        myBestTime = intent.getIntExtra("myBestTime",0);

        globalBestTime = intent.getIntExtra("globalBestTime",0);

        ((TextView) findViewById(R.id.textView2)).setText("Global Best Time: "+globalBestTime);
        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

    }

    Runnable timer = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(timer, 1000);
            TextView timeView = findViewById(R.id.textView);
            time++;
            timeView.setText("Time:"+time);

        }

    };


    public void changeTheme(int imageResource){
        themeid = imageResource;
        iv1.setImageResource(imageResource);
        iv2.setImageResource(imageResource);
        iv3.setImageResource(imageResource);
        iv4.setImageResource(imageResource);
        iv5.setImageResource(imageResource);
        iv6.setImageResource(imageResource);

    }


    public void onClickTile1(View v) {

        flippedUp++;

        if(flippedUp == 1){
            iv1.setImageResource(R.drawable.cy);
            firstTileResource = iv1;
            firstTileHidden = 1;
        }

        if(flippedUp == 2) {
            iv1.setImageResource(R.drawable.cy);

            if(firstTileHidden != 1){
                firstTileResource.setImageResource(themeid);
                iv1.setImageResource(themeid);
            }
            else{
                score++;
                if (score == 3){
                    findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(timer);
                    if(time < myBestTime){
                        myBestTime = time;
                        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                        findViewById(R.id.next).setVisibility(View.VISIBLE);
                    }
                }
            }

            flippedUp = 0;

        }
    }

    public void onClickTile2(View v) {

        flippedUp++;

        if(flippedUp == 1){
            iv2.setImageResource(R.drawable.cycircle);
            firstTileResource = iv2;
            firstTileHidden = 2;
        }

        if(flippedUp == 2) {
            iv2.setImageResource(R.drawable.cycircle);

            if(firstTileHidden != 2){
                firstTileResource.setImageResource(themeid);
                iv2.setImageResource(themeid);

            }
            else{
                score++;
                if (score == 3){
                    findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(timer);
                    if(time < myBestTime){
                        myBestTime = time;
                        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                        findViewById(R.id.next).setVisibility(View.VISIBLE);
                    }
                }
            }

            flippedUp = 0;

        }
    }

    public void onClickTile3(View v) {

        flippedUp++;

        if(flippedUp == 1){
            iv3.setImageResource(R.drawable.statelogo);
            firstTileResource = iv3;
            firstTileHidden = 3;
        }

        if(flippedUp == 2) {
            iv3.setImageResource(R.drawable.statelogo);

            if(firstTileHidden != 3){
                firstTileResource.setImageResource(themeid);
                iv3.setImageResource(themeid);
            }
            else{
                score++;
                if (score == 3){
                    findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(timer);
                    if(time < myBestTime){
                        myBestTime = time;
                        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                        findViewById(R.id.next).setVisibility(View.VISIBLE);
                    }
                }

            }

            flippedUp = 0;

        }
    }

    public void onClickTile4(View v) {

        flippedUp++;

        if(flippedUp == 1){
            iv4.setImageResource(R.drawable.cy);
            firstTileResource = iv4;
            firstTileHidden = 1;
        }

        if(flippedUp == 2) {
            iv4.setImageResource(R.drawable.cy);


            if(firstTileHidden != 1){
                firstTileResource.setImageResource(themeid);
                iv4.setImageResource(themeid);
            }
            else{
                score++;
                if (score == 3){
                    findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(timer);
                    if(time < myBestTime){
                        myBestTime = time;
                        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                        findViewById(R.id.next).setVisibility(View.VISIBLE);
                    }
                }
            }

            flippedUp = 0;

        }
    }

    public void onClickTile5(View v) {

        flippedUp++;

        if(flippedUp == 1){
            iv5.setImageResource(R.drawable.statelogo);
            firstTileResource = iv5;
            firstTileHidden = 3;
        }

        if(flippedUp == 2) {
            iv5.setImageResource(R.drawable.statelogo);

            if(firstTileHidden != 3){
                firstTileResource.setImageResource(themeid);
                iv5.setImageResource(themeid);
            }
            else{
                score++;
                if (score == 3){
                    findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(timer);
                    if(time < myBestTime){
                        myBestTime = time;
                        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                        findViewById(R.id.next).setVisibility(View.VISIBLE);
                    }
                }
            }

            flippedUp = 0;

        }
    }

    public void onClickTile6(View v) {

        flippedUp++;

        if(flippedUp == 1){
            iv6.setImageResource(R.drawable.cycircle);
            firstTileResource = iv6;
            firstTileHidden = 2;
        }

        if(flippedUp == 2) {
            iv6.setImageResource(R.drawable.cycircle);

            if(firstTileHidden != 2){
                firstTileResource.setImageResource(themeid);
                iv6.setImageResource(themeid);
            }
            else{
                score++;
                if (score == 3){
                    findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                    handler.removeCallbacks(timer);
                    if(time < myBestTime){
                        myBestTime = time;
                        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                        findViewById(R.id.next).setVisibility(View.VISIBLE);
                    }
                }
            }

            flippedUp = 0;

        }
    }

    public void mainMenu(View v){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void Next(View v){

        //use this to go to next round
    }


}