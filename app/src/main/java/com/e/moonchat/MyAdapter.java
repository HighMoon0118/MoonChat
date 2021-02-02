package com.e.moonchat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MyData> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private String roomID;
        private TextView roomName;
        private View view;

        public MyViewHolder(LinearLayout mLayout) {
            super(mLayout);
            roomName = (TextView) mLayout.findViewById(R.id.textView_RoomName);
            view = mLayout;
        }
    }

    public MyAdapter(List<MyData> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myroom, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyData myData = mDataset.get(position);

        if(myData!=null) {
            holder.roomID=myData.getRoomID();
            holder.roomName.setText(myData.getRoomName());
        }
    }

    @Override
    public int getItemCount() {
        return mDataset==null ? 0 : mDataset.size();
    }

    public void addData(MyData myData) {
        mDataset.add(myData);
        notifyItemInserted(mDataset.size()-1);
    }

}
