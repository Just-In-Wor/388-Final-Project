package com.example.matchthetiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String mUsername;
    private String mPhotoUrl;
    public static final String ANONYMOUS = "anonymous";


    private int check;
    private Intent i;
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
    private GoogleSignInClient mSignInClient;

    private ArrayList<Integer> scoresForUserRound1;
    private ArrayList<Integer> scoresForUserRound2;
    private ArrayList<Integer> scoresForUserRound3;


    private ArrayList<String>scoresForGlobalRound1;
    private ArrayList<String>scoresForGlobalRound2;
    private ArrayList<String>scoresForGlobalRound3;

    //theme
 
    private String theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = getIntent();
        theme = i.getStringExtra("theme");


        if(theme != null){

            switch(theme) {
                case "winter":
                    findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.winterbackground);
                    break;
                case "fall":
                    findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.fallbackground);
                    break;
                case "spring":
                    findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.springbackground);
                    break;
                case "summer":
                    findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.summerbackground);
                    break;
            }


        }
        else{
            Calendar currentDateAndTime = Calendar.getInstance();

            Calendar springtime = Calendar.getInstance();
            Calendar summerTime = Calendar.getInstance();
            Calendar fallTime = Calendar.getInstance();
            Calendar winterTime = Calendar.getInstance();

            springtime.set(currentDateAndTime.get(Calendar.YEAR), 3, 21,0,0);
            summerTime.set(currentDateAndTime.get(Calendar.YEAR), 6, 21,0,0);
            fallTime.set(currentDateAndTime.get(Calendar.YEAR), 9, 21,0,0);
            winterTime.set(currentDateAndTime.get(Calendar.YEAR), 12, 21,0,0);

            //If it the current date is in the spring
            if(currentDateAndTime.after(springtime) && currentDateAndTime.before(summerTime)){
                findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.springbackground);
            }
            //If it the current date is in the summer
            else if(currentDateAndTime.after(summerTime) && currentDateAndTime.before(fallTime)){
                findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.summerbackground);
            }
            //If it the current date is in the fall
            else if(currentDateAndTime.after(fallTime) && currentDateAndTime.before(winterTime)){
                findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.fallbackground);
            }
            //If it the current date is in the winter
            else{
                findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.winterbackground);
            }
        }
         //Set default username is anonymous.
        mUsername = ANONYMOUS;

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();



        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

        check = 0;
        //Round 1 Personal Scores
        scoresForUserRound1 = new ArrayList<>();
        DatabaseReference referenceRound1 = FirebaseDatabase.getInstance().getReference();
        Query queryRound1 = referenceRound1.child("Round1").child(mFirebaseUser.getUid()).orderByValue();
        queryRound1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForUserRound1.add(Integer.parseInt(issue.getValue().toString()));



                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //Round 2 Personal Scores
        scoresForUserRound2 = new ArrayList<>();
        DatabaseReference referenceRound2 = FirebaseDatabase.getInstance().getReference();
        Query queryRound2 = referenceRound2.child("Round2").child(mFirebaseUser.getUid()).orderByValue();
        queryRound2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForUserRound2.add(Integer.parseInt(issue.getValue().toString()));


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Round 3 Personal Scores
        scoresForUserRound3 = new ArrayList<>();
        DatabaseReference referenceRound3 = FirebaseDatabase.getInstance().getReference();
        Query queryRound3 = referenceRound3.child("Round3").child(mFirebaseUser.getUid()).orderByValue();
        queryRound3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForUserRound3.add(Integer.parseInt(issue.getValue().toString()));


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Round 1 Global Scores
        scoresForGlobalRound1 = new ArrayList<>();
        DatabaseReference referenceGlobalRound1 = FirebaseDatabase.getInstance().getReference();
        Query queryGlobalRound1 = referenceGlobalRound1.child("globalRound1").orderByValue();
        queryGlobalRound1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForGlobalRound1.add((issue.getKey().toString())+": " +issue.getValue().toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Round 2 Global Scores
        scoresForGlobalRound2 = new ArrayList<>();
        DatabaseReference referenceGlobalRound2 = FirebaseDatabase.getInstance().getReference();
        Query queryGlobalRound2 = referenceRound2.child("globalRound2").orderByValue();
        queryGlobalRound2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForGlobalRound2.add((issue.getKey().toString())+": " + issue.getValue().toString());


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Round 3 Global Scores
        scoresForGlobalRound3 = new ArrayList<>();
        DatabaseReference referenceGlobalRound3 = FirebaseDatabase.getInstance().getReference();
        Query queryGlobalRound3 = referenceRound3.child("globalRound3").orderByValue();
        queryGlobalRound3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForGlobalRound3.add((issue.getKey().toString())+": " + issue.getValue().toString());


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void start(View v){
        Intent intent = new Intent(this, Round1.class);

        intent.putExtra("theme", theme);
        if(scoresForUserRound1.size() != 0) {
            intent.putExtra("round1UserScore", scoresForUserRound1.get(0));
        }
        else{
            intent.putExtra("round1UserScore", 0);
        }
        if(scoresForUserRound2.size() != 0) {
            intent.putExtra("round2UserScore", scoresForUserRound2.get(0));
        }
        else{
            intent.putExtra("round2UserScore", 0);
        }
        if(scoresForUserRound3.size() != 0) {
            intent.putExtra("round3UserScore", scoresForUserRound3.get(0));
        }
        else{
            intent.putExtra("round3UserScore", 0);
        }
        if(scoresForGlobalRound1.size() != 0) {
            intent.putExtra("round1GlobalScore", scoresForGlobalRound1.get(0));
        }
        else{
            intent.putExtra("round1GlobalScore", " ");
        }
        if(scoresForGlobalRound2.size() != 0) {
            intent.putExtra("round2GlobalScore", scoresForGlobalRound2.get(0));
        }
        else{
            intent.putExtra("round2GlobalScore", " ");
        }
        if(scoresForGlobalRound3.size() != 0) {
            intent.putExtra("round3GlobalScore", scoresForGlobalRound3.get(0));
        }
        else{
            intent.putExtra("round3GlobalScore", " ");
        }
        startActivity(intent);
    }

    public void myHighScores(View v){
        Intent intent = new Intent(this, MyHighScores.class);

        intent.putExtra("theme", theme);
        intent.putExtra("username",mUsername);

        intent.putExtra("scoresRound1",scoresForUserRound1);
        intent.putExtra("scoresRound2",scoresForUserRound2);
        intent.putExtra("scoresRound3",scoresForUserRound3);

        startActivity(intent);
    }

    public void globalHighScores(View v){
        Intent intent = new Intent(this, GlobalHighScores.class);

        intent.putExtra("theme", theme);
        intent.putExtra("myBestTime", 15);
        intent.putExtra("scoresGlobalRound1",scoresForGlobalRound1);
        intent.putExtra("scoresGlobalRound2",scoresForGlobalRound2);
        intent.putExtra("scoresGlobalRound3",scoresForGlobalRound3);
        startActivity(intent);
    }

    public void springTheme(View v){

        theme = "spring";
        findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.springbackground);

    }

    public void fallTheme(View v){
        theme = "fall";
        findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.fallbackground);
    }

    public void winterTheme(View v){
        theme = "winter";
        findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.winterbackground);

    }

    public void summerTheme(View v){

        theme = "summer";
        findViewById(R.id.homeScreen).setBackgroundResource(R.drawable.summerbackground);


    }
}