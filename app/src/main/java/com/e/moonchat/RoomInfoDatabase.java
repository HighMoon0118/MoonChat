package com.e.moonchat;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomInfo.class}, version = 1)
public abstract class RoomInfoDatabase extends RoomDatabase {
    public abstract RoomInfoDao getRoomInfoDao();
}
