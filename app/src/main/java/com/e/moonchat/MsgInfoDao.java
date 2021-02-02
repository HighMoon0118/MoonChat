package com.e.moonchat;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MsgInfoDao {

    @Insert
    void insert(MsgInfo msgInfo);

    @Update
    void update(MsgInfo msgInfo);

    @Delete
    void delete(MsgInfo msgInfo);

    @Query("SELECT * FROM MsgInfo")
    List<MsgInfo> getAll();
}
