package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{
    EditText name, emailId, password, phone;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.editText1);
        emailId = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        phone = findViewById(R.id.editText4);
        btnSignUp = findViewById(R.id.button);
        tvSignIn = findViewById(R.id.textView);

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String phn = phone.getText().toString();
                if (name1.isEmpty()) {
                    name.setError("Please enter your name");
                    name.requestFocus();
                } else if (email.isEmpty()) {
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                } else if (phn.isEmpty()) {
                    phone.setError("Please enter your phone number");
                    phone.requestFocus();
                } else if (name1.isEmpty() && email.isEmpty() && pwd.isEmpty() && phn.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields are Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "SignUp Unsuccessful, please try again!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }
                        }
                    });


                }
                else{
                    Toast.makeText(LoginActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,Main1Activity.class);
                startActivity(i);
            }
        });
    }





}
