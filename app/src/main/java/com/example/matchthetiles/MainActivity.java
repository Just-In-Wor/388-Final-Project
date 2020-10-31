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

public class MainActivity extends AppCompatActivity {

    private String mUsername;
    private String mPhotoUrl;
    public static final String ANONYMOUS = "anonymous";


    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
    private GoogleSignInClient mSignInClient;

    private ArrayList<Integer> scoresForUserRound1;
    private ArrayList<Integer> scoresForUserRound2;
    private ArrayList<Integer> scoresForUserRound3;
    private ArrayList<Integer> scoresForUserRound4;
    private ArrayList<Integer> scoresForUserRound5;

    private ArrayList<Integer>scoresForGlobalRound1;
  
    //theme
 
    private String theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
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


        //Round 4 Personal Scores
        scoresForUserRound4 = new ArrayList<>();
        DatabaseReference referenceRound4 = FirebaseDatabase.getInstance().getReference();
        Query queryRound4 = referenceRound4.child("Round4").child(mFirebaseUser.getUid()).orderByValue();
        queryRound4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForUserRound4.add(Integer.parseInt(issue.getValue().toString()));


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Round 5 Personal Scores
        scoresForUserRound5 = new ArrayList<>();
        DatabaseReference referenceRound5 = FirebaseDatabase.getInstance().getReference();
        Query queryRound5 = referenceRound5.child("Round5").child(mFirebaseUser.getUid()).orderByValue();
        queryRound5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot issue : snapshot.getChildren() ){
                        scoresForUserRound5.add(Integer.parseInt(issue.getValue().toString()));

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
        Query queryGlobalRound1 = referenceGlobalRound1.child("Round1");
        queryGlobalRound1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    ArrayList<String> s = new ArrayList<String>();
                    for(DataSnapshot issue : snapshot.getChildren() ){
                        s.add(issue.getKey());
                    }

                    for(int i = 0; i < s.size();i++){

                        DatabaseReference referenceInsideGlobalRound1 = FirebaseDatabase.getInstance().getReference();
                        Query queryInsideGlobalRound1 = referenceInsideGlobalRound1.child("Round5").child(s.get(i)).orderByValue();

                        queryInsideGlobalRound1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.exists()){

                                    for(DataSnapshot issue : snapshot.getChildren() ){
                                        scoresForGlobalRound1.add(Integer.parseInt(issue.getValue().toString()));

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        //Round 2 Global Scores
//        scoresForUserRound2 = new ArrayList<>();
//        DatabaseReference referenceRound2 = FirebaseDatabase.getInstance().getReference();
//        Query queryRound2 = referenceRound2.child("Round2").child(mFirebaseUser.getUid()).orderByValue();
//        queryRound2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    for(DataSnapshot issue : snapshot.getChildren() ){
//                        scoresForUserRound2.add(Integer.parseInt(issue.getValue().toString()));
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        //Round 3 Global Scores
//        scoresForUserRound3 = new ArrayList<>();
//        DatabaseReference referenceRound3 = FirebaseDatabase.getInstance().getReference();
//        Query queryRound3 = referenceRound3.child("Round3").child(mFirebaseUser.getUid()).orderByValue();
//        queryRound3.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    for(DataSnapshot issue : snapshot.getChildren() ){
//                        scoresForUserRound3.add(Integer.parseInt(issue.getValue().toString()));
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//        //Round 4 Global Scores
//        scoresForUserRound4 = new ArrayList<>();
//        DatabaseReference referenceRound4 = FirebaseDatabase.getInstance().getReference();
//        Query queryRound4 = referenceRound4.child("Round4").child(mFirebaseUser.getUid()).orderByValue();
//        queryRound4.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    for(DataSnapshot issue : snapshot.getChildren() ){
//                        scoresForUserRound4.add(Integer.parseInt(issue.getValue().toString()));
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//        //Round 5 Global Scores
//        scoresForUserRound5 = new ArrayList<>();
//        DatabaseReference referenceRound5 = FirebaseDatabase.getInstance().getReference();
//        Query queryRound5 = referenceRound5.child("Round5").child(mFirebaseUser.getUid()).orderByValue();
//        queryRound5.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    for(DataSnapshot issue : snapshot.getChildren() ){
//                        scoresForUserRound5.add(Integer.parseInt(issue.getValue().toString()));
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }


    public void start(View v){
        Intent intent = new Intent(this, Round1.class);

        intent.putExtra("theme", theme);
        intent.putExtra("myBestTime", 15);
        intent.putExtra("globalBestTime", 9);
        startActivity(intent);
    }

    public void myHighScores(View v){
        Intent intent = new Intent(this, MyHighScores.class);

        intent.putExtra("theme", theme);
        intent.putExtra("myBestTime", 15);
        intent.putExtra("globalBestTime", 9);
        intent.putExtra("username",mUsername);
        intent.putExtra("scoresRound1",scoresForUserRound1);
        intent.putExtra("scoresRound2",scoresForUserRound2);
        intent.putExtra("scoresRound3",scoresForUserRound3);
        intent.putExtra("scoresRound4",scoresForUserRound4);
        intent.putExtra("scoresRound5",scoresForUserRound5);


        startActivity(intent);
    }

    public void globalHighScores(View v){
        Intent intent = new Intent(this, GlobalHighScores.class);

        intent.putExtra("theme", theme);
        intent.putExtra("myBestTime", 15);
        intent.putExtra("globalBestTime", 9);
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