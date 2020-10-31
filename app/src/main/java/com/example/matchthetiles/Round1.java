package com.example.matchthetiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Telephony;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Round1 extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;

    //keeps track of how many are flipped up currently
    private int flippedUp;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private String globalRoundScore2;
    private String globalRoundScore3;
    private int userRoundScore2;
    private int userRoundScore3;

    //id of the two tiles flipped up to know if they match
    //1 for cy, 2 for cycircle and 3 for statelogo
    private int firstTileHidden;

    private ArrayList<Integer> scoresForUserRound1;

    //Resource ids of the tiles flipped
    private ImageView firstTileResource;

    private DatabaseReference mDatabase;

    private int themeid;

    Handler handler = new Handler();

    int time;

    int score;

    int myBestTime;

    String globalBestTime;

    String theme;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        mFirebaseAuth = FirebaseAuth.getInstance();
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

        switch(theme) {
            case "winter":
                changeTheme(R.drawable.snowflaketile);
                findViewById(R.id.round1Layout).setBackgroundResource(R.drawable.winterbackground);
            break;
            case "fall":
                changeTheme(R.drawable.leaf);
                findViewById(R.id.round1Layout).setBackgroundResource(R.drawable.fallbackground);
                break;
            case "spring":
                changeTheme(R.drawable.spring);
                findViewById(R.id.round1Layout).setBackgroundResource(R.drawable.springbackground);
                break;
            case "summer":
                changeTheme(R.drawable.summer);
                findViewById(R.id.round1Layout).setBackgroundResource(R.drawable.summerbackground);
                break;
        }

        myBestTime = 0;

        globalBestTime = "";

        myBestTime = intent.getIntExtra("round1UserScore",0);
        globalBestTime = intent.getStringExtra("round1GlobalScore");
        ((TextView) findViewById(R.id.textView2)).setText("Global Best Time: "+globalBestTime);
        ((TextView) findViewById(R.id.textView3)).setText("My Best Time: "+myBestTime);

        userRoundScore2 = intent.getIntExtra("round2UserScore",0);
        userRoundScore3 = intent.getIntExtra("round3UserScore",0);

        globalRoundScore2 = intent.getStringExtra("round2GlobalScore");
        globalRoundScore3 = intent.getStringExtra("round3GlobalScore");

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
                        if (score == 3){
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
                        if (score == 3){
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
                        if (score == 3){
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
                        if (score == 3){
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
                        if (score == 3){
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
                        if (score == 3){
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

        updatePersonalScore();
        updateGlobalScore();
        //use this to go to next round
        Intent intent = new Intent(this,Round2.class);
        intent.putExtra("theme", theme);

        intent.putExtra("round2UserScore",userRoundScore2);
        intent.putExtra("round3UserScore",userRoundScore3);

        intent.putExtra("round2GlobalScore",globalRoundScore2);
        intent.putExtra("round3GlobalScore",globalRoundScore3);
        startActivity(intent);
    }

    private void updatePersonalScore(){
        final int roundScore = time;
        DatabaseReference referenceRound1 = FirebaseDatabase.getInstance().getReference();
        Query queryRound1 = referenceRound1.child("Round1").child(mFirebaseAuth.getUid()).orderByValue();
        queryRound1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean notUpdated = false;
                int count = 1;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    int testing = Integer.parseInt(singleSnapshot.getValue().toString());
                        if(testing > roundScore) {
                            notUpdated = true;
                        }
                    if(count == 5 && notUpdated != false) {
                        String upDateKey = singleSnapshot.getKey().toString();
                        for(int i=5;i>0;--i) {
                            DatabaseReference myRef2 = database.getReference("Round1/" + mFirebaseAuth.getUid() + "/" + i);
                            if (myRef2.getKey().equals(upDateKey)) {
                                myRef2.setValue(roundScore);
                            }
                        }
                    }
                    count++;
                    }
                if(count<6){
                    DatabaseReference myRef2 = database.getReference("Round1/" + mFirebaseAuth.getUid() + "/" + count);
                    myRef2.setValue(roundScore);
                }
                }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("error", "onCancelled", databaseError.toException());
            }
        });
    }

    private void updateGlobalScore() {
        mFirebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference referenceRound1 = FirebaseDatabase.getInstance().getReference();
        Query queryRound1 = referenceRound1.child("globalRound1");

        queryRound1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("globalRound1/" + mFirebaseUser.getDisplayName());
                if(snapshot.exists()){
                    for(DataSnapshot issue : snapshot.getChildren() ){
                        if(Integer.parseInt(issue.getValue().toString()) > time && issue.getKey().equals(mFirebaseUser.getDisplayName())){
                            myRef.setValue(time);
                        }
                    }
                }
                else{
                    myRef.setValue(time);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}