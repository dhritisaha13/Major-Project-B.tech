package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout,btnParticipatory,btnOpportunistic;
    TextView loggedIn,dataCollection,partD,opporD;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout=findViewById(R.id.button1);
        btnParticipatory=findViewById(R.id.button2);
        btnOpportunistic=findViewById(R.id.button3);
        loggedIn=findViewById(R.id.textView1);
        dataCollection=findViewById(R.id.textView2);
        partD=findViewById(R.id.textView3);
        opporD=findViewById(R.id.textView4);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this,Main1Activity.class);
                startActivity(intent);
            }
        });

        btnOpportunistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,OpportunisticActivity.class);
                startActivity(intent);
            }
        });
    }

}
