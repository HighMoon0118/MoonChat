package com.e.moonchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private String mName;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewContent;
        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            textViewName = v.findViewById(R.id.textView_name);
            textViewContent = v.findViewById(R.id.textView_msg);
            rootView = v;
        }
    }

    public ChatAdapter(List<ChatData> myDataset, Context context, String myName) {
        mDataset = myDataset;
        mName = myName;
    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ChatData chat = mDataset.get(position);

        holder.textViewName.setText(chat.getName());
        holder.textViewContent.setText(chat.getMsg());

        if(mName.equals(chat.getName())) {
            holder.textViewContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END); //17이상만 가능
            holder.textViewName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else {
            holder.textViewContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.textViewName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
    }


    @Override
    public int getItemCount() {
        return mDataset==null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신
    }
}
