package com.example.user.got_guide;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

@Dao
public interface DataAccessObject {
    @Insert
    public void insertData(DataManager dataManager);

    @Query("select name from GotBase")
     public List<String> getAllName();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select * from GotBase where name=:Name")
    public data getHistory(String Name);
}
