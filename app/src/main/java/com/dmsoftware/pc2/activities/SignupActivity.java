package com.dmsoftware.pc2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dmsoftware.pc2.R;
import com.dmsoftware.pc2.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText nameEditText, lastNameEditText, countryEditText, biographyEditText, emailEditText, passwordEditText;

    FirebaseAuth auth;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        countryEditText = (EditText) findViewById(R.id.countryEditText);
        biographyEditText = (EditText) findViewById(R.id.biographyEditText);
        emailEditText = (EditText) findViewById(R.id.emailSEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordSEditText);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name, lastName, country, biography, email;
                name = nameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                country = countryEditText.getText().toString();
                biography = biographyEditText.getText().toString();
                email = emailEditText.getText().toString();

                user = new User(name,lastName,country,biography, email);

                auth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),
                                            "Tenemos un problema",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    saveUser(user);
                                    Intent intent=new Intent(SignupActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });
    }

    private void saveUser(User user) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("user").child(user.getEmail()).setValue(user);
    }
    //String id = databaseReference.push().getKey()
}
