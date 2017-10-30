package com.example.miras.chatapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Message> messageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessagesAdapter mAdapter;
    private ImageButton send;
    private EditText input;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        send = (ImageButton) findViewById(R.id.send);
        input = (EditText) findViewById(R.id.input);

        mAdapter = new MessagesAdapter(messageList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = mDatabase.getReference();
                DatabaseReference newRef = reference.push();

                Message newMessage = new Message(input.getText().toString(),
                        mAuth.getCurrentUser().getEmail().toString());
                newRef.child("text").setValue(newMessage.getMessageText());
                newRef.child("user").setValue(newMessage.getMessageUser());
                newRef.child("time").setValue(newMessage.getMessageTime()/*, new DatabaseReference.CompletionListener () {
                    public void onComplete (DatabaseError err, DatabaseReference ref){
                        mAdapter.notifyDataSetChanged();
                    }
                }*/);

              /*  Log.d("messs", newMessage.getMessageUser());
                messageList.add(newMessage);
                mAdapter.notifyDataSetChanged();*/
            }
        });

        DatabaseReference myRef = mDatabase.getReference();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String text = String.valueOf(dataSnapshot.child("text").getValue());
                String user = String.valueOf(dataSnapshot.child("user").getValue());
                String time = String.valueOf(dataSnapshot.child("time").getValue());

                if (time == "null" || user == "null" || text == "null") return;
                Message newMessage = new Message(text, user, time);
                Log.d("messs", newMessage.toString());

                messageList.add(newMessage);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String text = String.valueOf(dataSnapshot.child("text").getValue());
                String user = String.valueOf(dataSnapshot.child("user").getValue());
                String time = String.valueOf(dataSnapshot.child("time").getValue());

                if (time == "null" || user == "null" || text == "null") return;
                Message newMessage = new Message(text, user, time);
                Log.d("messs", newMessage.toString());

                messageList.add(newMessage);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        mAuth.signOut();
    }
}
