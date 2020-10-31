package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Round3 extends AppCompatActivity {


    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    private ImageView iv7;
    private ImageView iv8;
    private ImageView iv9;
    private ImageView iv10;

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
        setContentView(R.layout.activity_round3);


        handler.postDelayed(timer, 1000);
        flippedUp = 0;
        Intent intent = getIntent();
        score = 0;
        this.theme = intent.getStringExtra("theme");
        iv1 = findViewById(R.id.imageView);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        iv9 = findViewById(R.id.imageView9);
        iv10 = findViewById(R.id.imageView10);

        switch(theme) {
            case "winter":
                changeTheme(R.drawable.snowflaketile);
                findViewById(R.id.round3Layout).setBackgroundResource(R.drawable.winterbackground);
                break;
            case "fall":
                changeTheme(R.drawable.leaf);
                findViewById(R.id.round3Layout).setBackgroundResource(R.drawable.fallbackground);
                break;
            case "spring":
                changeTheme(R.drawable.spring);
                findViewById(R.id.round3Layout).setBackgroundResource(R.drawable.springbackground);
                break;
            case "summer":
                changeTheme(R.drawable.summer);
                findViewById(R.id.round3Layout).setBackgroundResource(R.drawable.summerbackground);
                break;
        }

        myBestTime = 0;

        globalBestTime = 0;

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
        iv7.setImageResource(imageResource);
        iv8.setImageResource(imageResource);
        iv9.setImageResource(imageResource);
        iv10.setImageResource(imageResource);

    }


    public void onClickTile1(View v) {

        flippedUp++;
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv1);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                iv10.setClickable(false);

                if(firstTileHidden != 1 && flippedUp == 2) {
                    iv1.setImageResource(R.drawable.cy);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv1.setImageResource(R.drawable.cy);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);
                iv10.setClickable(true);
                if(flippedUp == 1){
                    firstTileResource = iv1;
                    firstTileHidden = 1;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 1){
                        firstTileResource.setImageResource(themeid);
                        iv1.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();


    }

    public void onClickTile2(View v) throws InterruptedException {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv2);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                iv1.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                iv10.setClickable(false);
                if(firstTileHidden != 2 && flippedUp == 2) {
                    iv2.setImageResource(R.drawable.cycircle);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv2.setImageResource(R.drawable.cycircle);
                iv1.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);
                iv10.setClickable(true);
                if(flippedUp == 1){

                    firstTileResource = iv2;
                    firstTileHidden = 2;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 2){
                        firstTileResource.setImageResource(themeid);
                        iv2.setImageResource(themeid);

                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();



    }

    public void onClickTile3(View v) {

        flippedUp++;


        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv3);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                iv2.setClickable(false);
                iv1.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                iv10.setClickable(false);
                if(firstTileHidden != 3 && flippedUp == 2) {
                    iv3.setImageResource(R.drawable.statelogo);
                }


            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv3.setImageResource(R.drawable.statelogo);
                iv2.setClickable(true);
                iv1.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);
                iv10.setClickable(true);
                if(flippedUp == 1){

                    firstTileResource = iv3;
                    firstTileHidden = 3;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 3){
                        firstTileResource.setImageResource(themeid);
                        iv3.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }

                    }

                    flippedUp = 0;

                }


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();


    }

    public void onClickTile4(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv4);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                iv2.setClickable(false);
                iv3.setClickable(false);
                iv1.setClickable(false);
                iv5.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                iv10.setClickable(false);

                if(firstTileHidden != 1 && flippedUp == 2) {
                    iv4.setImageResource(R.drawable.cy);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv4.setImageResource(R.drawable.cy);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv1.setClickable(true);
                iv5.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);
                iv10.setClickable(true);
                if(flippedUp == 1){

                    firstTileResource = iv4;
                    firstTileHidden = 1;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 1){
                        firstTileResource.setImageResource(themeid);
                        iv4.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);
                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();


    }

    public void onClickTile5(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv5);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv1.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                iv10.setClickable(false);
                if(firstTileHidden != 3 && flippedUp == 2) {
                    iv5.setImageResource(R.drawable.statelogo);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv5.setImageResource(R.drawable.statelogo);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv1.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);
                iv10.setClickable(true);
                if(flippedUp == 1){

                    firstTileResource = iv5;
                    firstTileHidden = 3;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 3){
                        firstTileResource.setImageResource(themeid);
                        iv5.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();


    }

    public void onClickTile6(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv6);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv1.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                iv10.setClickable(false);
                if(firstTileHidden != 2 && flippedUp == 2) {
                    iv6.setImageResource(R.drawable.cycircle);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv6.setImageResource(R.drawable.cycircle);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv1.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);
                iv10.setClickable(true);
                if(flippedUp == 1){

                    firstTileResource = iv6;
                    firstTileHidden = 2;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 2){
                        firstTileResource.setImageResource(themeid);
                        iv6.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();



    }

    public void onClickTile7(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv7);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv1.setClickable(false);
                iv6.setClickable(false);
                if(firstTileHidden != 4 && flippedUp == 2) {
                    iv7.setImageResource(R.drawable.retro);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv7.setImageResource(R.drawable.retro);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv1.setClickable(true);
                iv6.setClickable(true);

                if(flippedUp == 1){

                    firstTileResource = iv7;
                    firstTileHidden = 4;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 4){
                        firstTileResource.setImageResource(themeid);
                        iv7.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    public void onClickTile8(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv8);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv1.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                if(firstTileHidden != 4 && flippedUp == 2) {
                    iv8.setImageResource(R.drawable.retro);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv8.setImageResource(R.drawable.retro);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv1.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);

                if(flippedUp == 1){

                    firstTileResource = iv8;
                    firstTileHidden = 4;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 4){
                        firstTileResource.setImageResource(themeid);
                        iv8.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    public void onClickTile9(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv9);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv1.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                if(firstTileHidden != 5 && flippedUp == 2) {
                    iv9.setImageResource(R.drawable.retroilogo);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv9.setImageResource(R.drawable.retroilogo);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv1.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);

                if(flippedUp == 1){

                    firstTileResource = iv9;
                    firstTileHidden = 5;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 5){
                        firstTileResource.setImageResource(themeid);
                        iv9.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    public void onClickTile10(View v) {

        flippedUp++;

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.flip);
        set.setTarget(iv10);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                iv2.setClickable(false);
                iv3.setClickable(false);
                iv4.setClickable(false);
                iv5.setClickable(false);
                iv1.setClickable(false);
                iv6.setClickable(false);
                iv7.setClickable(false);
                iv8.setClickable(false);
                iv9.setClickable(false);
                if(firstTileHidden != 5 && flippedUp == 2) {
                    iv10.setImageResource(R.drawable.retroilogo);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv10.setImageResource(R.drawable.retroilogo);
                iv2.setClickable(true);
                iv3.setClickable(true);
                iv4.setClickable(true);
                iv5.setClickable(true);
                iv1.setClickable(true);
                iv6.setClickable(true);
                iv7.setClickable(true);
                iv8.setClickable(true);
                iv9.setClickable(true);

                if(flippedUp == 1){

                    firstTileResource = iv10;
                    firstTileHidden = 5;
                }

                if(flippedUp == 2) {

                    if(firstTileHidden != 5){
                        firstTileResource.setImageResource(themeid);
                        iv10.setImageResource(themeid);
                    }
                    else{
                        score++;
                        if (score == 5){
                            findViewById(R.id.winTextView).setVisibility(View.VISIBLE);
                            handler.removeCallbacks(timer);
                            if(time < myBestTime){
                                myBestTime = time;
                                ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

                            }
                            findViewById(R.id.next).setVisibility(View.VISIBLE);
                        }
                    }

                    flippedUp = 0;

                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }


    public void mainMenu(View v){

        //use this to go to next round
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("theme", theme);
        startActivity(intent);
    }

    public void Next(View v){

        //use this to go to next round
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("theme", theme);
        startActivity(intent);
    }

}