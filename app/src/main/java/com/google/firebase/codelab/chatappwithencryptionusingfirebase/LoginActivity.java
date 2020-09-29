package com.google.firebase.codelab.chatappwithencryptionusingfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailID;
    EditText password;
    Button signUp;
    Button logIn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailID=findViewById(R.id.editText11);
        password=findViewById(R.id.editText22);

        logIn = findViewById(R.id.button11);
        signUp = findViewById(R.id.button22);

        firebaseAuth=FirebaseAuth.getInstance();
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailID.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty()){
                    emailID.setError("Please enter Email ID ");
                    emailID.requestFocus();
                }else if(pwd.isEmpty()){
                    password.setError(" please enter Password ");
                    password.requestFocus();
                }else if(!(email.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,details.class));
                            }
                        }
                    });
                }


            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

    }
}