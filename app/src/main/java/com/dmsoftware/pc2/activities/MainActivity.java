package com.dmsoftware.pc2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dmsoftware.pc2.R;
import com.dmsoftware.pc2.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView name, lastName, country, biography;
    Button abilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameTextView);
        lastName = findViewById(R.id.lastNameTextView);
        country = findViewById(R.id.countryTextView);
        biography = findViewById(R.id.biographyTextView);
        abilities = findViewById(R.id.addButton);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("user").orderByChild("email").equalTo(firebaseUser.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        //Getting the data from snapshot
                        User user = issue.getValue(User.class);

                        name.setText(user.getName());
                        lastName.setText(user.getLastName());
                        country.setText(user.getCountry());
                        biography.setText(user.getBiography());
                    }

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Tenemos un problema",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),
                        "Tenemos un problema",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
