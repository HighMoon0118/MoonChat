package com.e.moonchat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomFrag extends Fragment {

    private String userName = "운광";

    private View view;
    private Button bt_makeRoom;

    private List<RoomData> mDataset;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    ChildEventListener cel;

    RoomInfoDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag2, container, false);
        bt_makeRoom = (Button) view.findViewById(R.id.bt_makeRoom);
        mDataset = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.rooms_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RoomAdapter(mDataset);
        recyclerView.setAdapter(mAdapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("room");

        db = Room.databaseBuilder(getContext().getApplicationContext(), RoomInfoDatabase.class, "roomInfo").allowMainThreadQueries().build();

        ((RoomAdapter)mAdapter).setOnRoomClickListener(new RoomAdapter.OnRoomClickListener() { // 방을 클릭했을 때
            @Override
            public void onRoomClick(View v, String roomID, int position) {

                if(db.getRoomInfoDao().hasRoom(roomID)==null) { // roomInfo 테이블에 room 추가
                    RoomInfo roomInfo = new RoomInfo();
                    roomInfo.roomID = roomID;
                    roomInfo.roomName=((TextView)v).getText().toString();
                    roomInfo.userID=userName;

                    db.getRoomInfoDao().insert(roomInfo);
                }

                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("roomID", roomID);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        bt_makeRoom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final EditText et = new EditText(getActivity());
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("채팅방 이름 입력");
                builder.setView(et);
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface di, int i){

                        RoomData room = new RoomData();
                        room.setRoomName(et.getText().toString());
                        room.setHostName("호스트 이름");
                        myRef.push().setValue(room);
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        cel = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                RoomData room = snapshot.getValue(RoomData.class);
                room.setRoomID(snapshot.getKey().toString());
                ((RoomAdapter)mAdapter).addRoom(room);
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
        };

        myRef.addChildEventListener(cel);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        myRef.child("room").removeEventListener(cel);
    }
}
