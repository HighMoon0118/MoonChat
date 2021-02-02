package com.e.moonchat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomInfo {
    @PrimaryKey
    @NonNull
    public String roomID;
    public String userID;
    public String roomName;
}
