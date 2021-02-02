package com.e.moonchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends Activity {

    private String roomID;
    private String userID;
    private String userName;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ChatData> chatList;
    private String mName = "user1";

    private EditText editText_chat;
    private Button bt_send;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatview);

        editText_chat = findViewById(R.id.textView_chat);
        bt_send = findViewById(R.id.button_send);

        Intent intent = getIntent();
        roomID = intent.getExtras().getString("roomID");
        userName = intent.getExtras().getString("userName");

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText_chat.getText().toString();

                if(msg!=null){
                    ChatData chat = new ChatData();
                    chat.setName(mName);
                    chat.setMsg(msg);
                    myRef.child(roomID).child("msg").push().setValue(chat);
                }
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, ChatActivity.this , mName);
        recyclerView.setAdapter(mAdapter);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("room");

        myRef.child(roomID).child("msg").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatData chat = snapshot.getValue(ChatData.class);
                ((ChatAdapter)mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
