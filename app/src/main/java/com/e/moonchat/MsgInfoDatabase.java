package com.e.moonchat;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MsgInfo.class}, version = 1)
public abstract class MsgInfoDatabase extends RoomDatabase {
    public abstract MsgInfoDao getMsgInfoDao();
}
