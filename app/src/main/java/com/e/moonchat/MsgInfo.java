package com.e.moonchat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MsgInfo {
    @PrimaryKey
    @NonNull
    public String roomID;
    public String lastMsg;
    public String lastTime;
    public int notReadMsg;
}
