package com.example.matchthetiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String mUsername;
    private String mPhotoUrl;
    public static final String ANONYMOUS = "anonymous";


    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
    private GoogleSignInClient mSignInClient;
  
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
                    findViewById(R.id.homeScreen).setBackgroundColor(Color.GREEN);
                    break;
                case "fall":
                    findViewById(R.id.homeScreen).setBackgroundColor(Color.parseColor("#ffa500"));
                    break;
                case "spring":
                    findViewById(R.id.homeScreen).setBackgroundColor(Color.YELLOW);
                    break;
                case "summer":
                    findViewById(R.id.homeScreen).setBackgroundColor(Color.BLUE);
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