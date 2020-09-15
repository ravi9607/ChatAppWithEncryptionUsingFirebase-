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

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    EditText emailID;
    EditText password;
    Button signUp;
    Button logIn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailID=findViewById(R.id.editText1);
        password=findViewById(R.id.editText2);
        signUp=findViewById(R.id.button1);
        logIn = findViewById(R.id.button2);
        firebaseAuth=FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
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
                }else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"both fields is empty",Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this," Unable to SignUp ",Toast.LENGTH_SHORT).show();
                            }else {
                                startActivity(new Intent(MainActivity.this,Chat_Screen.class));

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this," Error Ocurred! ",Toast.LENGTH_SHORT).show();

                }

            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}