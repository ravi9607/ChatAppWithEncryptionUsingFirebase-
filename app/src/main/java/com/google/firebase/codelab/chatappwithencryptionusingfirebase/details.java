package com.google.firebase.codelab.chatappwithencryptionusingfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class details extends AppCompatActivity {
    private EditText name;
    private EditText age;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name=findViewById(R.id.editText1);
        age=findViewById(R.id.editText2);
        save=findViewById(R.id.button);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editname = name.getText().toString();
                String editage= age.getText().toString();
                if(editname.isEmpty()){
                    Toast.makeText(details.this,"Please Enter Name", Toast.LENGTH_LONG).show();
                }else if(editage.isEmpty()){
                    Toast.makeText(details.this,"Please Age Name", Toast.LENGTH_LONG).show();
                }else if(editname.isEmpty()&&editage.isEmpty()){
                    Toast.makeText(details.this,"Please Enter Name", Toast.LENGTH_LONG).show();
                    Toast.makeText(details.this,"Please Age Name", Toast.LENGTH_LONG).show();
                }else {
                    FirebaseDatabase.getInstance().getReference().child("ADMIN").push().child("Name&Age").push().child("Name").setValue(editname);

                }
            }
        });
    }
}