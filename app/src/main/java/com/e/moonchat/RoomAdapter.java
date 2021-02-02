package com.e.moonchat;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {

    private List<RoomData> mDataset;

    public interface  OnRoomClickListener {  //커스텀 리스너
        void onRoomClick(View v, String rID, int position);
    }

    private OnRoomClickListener mListener = null;

    public void setOnRoomClickListener(OnRoomClickListener listener) {
        this.mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private String roomID;
        private TextView roomName;
        private TextView hostName;
        private View view;

        public MyViewHolder(LinearLayout mLayout) {
            super(mLayout);
            roomName = (TextView) mLayout.findViewById(R.id.textView_RoomName);
            hostName = (TextView) mLayout.findViewById(R.id.textView_HostName);
            view = mLayout;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){

                        if(mListener!=null) {
                            mListener.onRoomClick(roomName, roomID, pos);
                        }
                    }
                }
            });
        }
    }


    public RoomAdapter(List<RoomData> myDataset) {
        mDataset = myDataset;
    }


    @Override
    public RoomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_room, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RoomData room = mDataset.get(position);

        if(room!=null){
            holder.roomID=room.getRoomID();
            holder.roomName.setText(room.getRoomName());
            holder.hostName.setText(room.getHostName());
        }

    }

    @Override
    public int getItemCount() {
        return mDataset==null ? 0 : mDataset.size();
    }

    public RoomData getRoom(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addRoom(RoomData room) {
        mDataset.add(room);
        notifyItemInserted(mDataset.size()-1); //갱신
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

