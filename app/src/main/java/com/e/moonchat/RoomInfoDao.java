package com.e.moonchat;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomInfoDao {

    @Insert
    void insert(RoomInfo roomInfo);

    @Update
    void update(RoomInfo roomInfo);

    @Delete
    void delete(RoomInfo roomInfo);

    @Query("SELECT roomID FROM RoomInfo WHERE roomID is :id LIMIT 1")
    String hasRoom(String id);

    @Query("SELECT * FROM RoomInfo")
    List<RoomInfo> getAll();
}
