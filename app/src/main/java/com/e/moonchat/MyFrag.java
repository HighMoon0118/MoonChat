package com.e.moonchat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MyFrag extends Fragment {

    private View view;

    private List<MyData> mDataset;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    RoomInfoDatabase db;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag1, container, false);
        mDataset = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(mDataset);
        recyclerView.setAdapter(mAdapter);

        db = Room.databaseBuilder(getContext().getApplicationContext(), RoomInfoDatabase.class, "roomInfo").allowMainThreadQueries().build();

        List<RoomInfo> list = db.getRoomInfoDao().getAll();
        for(RoomInfo next : list) {
            MyData myData = new MyData();
            myData.setRoomID(next.roomID);
            myData.setRoomName(next.roomName);
            ((MyAdapter)mAdapter).addData(myData);
        }

        return view;
    }
}
