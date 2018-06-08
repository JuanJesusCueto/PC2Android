package com.dmsoftware.pc2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dmsoftware.pc2.R;
import com.dmsoftware.pc2.adapters.UsersAdapter;
import com.dmsoftware.pc2.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    Button filterButton;
    EditText searchEditText;
    RecyclerView usersRecyclerView;
    UsersAdapter usersAdapter;
    RecyclerView.LayoutManager usersLayoutManager;
    DatabaseReference databaseReference;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        users = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        usersAdapter = new UsersAdapter(users);

        filterButton = findViewById(R.id.filterButton);
        searchEditText = findViewById(R.id.filterEditText);
        usersRecyclerView = findViewById(R.id.filterRecyclerView);

        usersLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        usersRecyclerView.setAdapter(usersAdapter);
        usersRecyclerView.setLayoutManager(usersLayoutManager);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFilter();
            }
        });
    }

    private void updateFilter() {
        Query query = databaseReference.child("user").orderByChild("name").equalTo(searchEditText.getText().toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        //Getting the data from snapshot
                        User user = issue.getValue(User.class);
                        users.add(user);
                    }
                    usersAdapter.setUsers(users);
                    usersAdapter.notifyDataSetChanged();
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
