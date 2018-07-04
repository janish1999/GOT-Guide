package com.example.user.got_guide;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {DataManager.class},version = 1  )
    @TypeConverters({converters.class})
    public abstract class MyDatabase extends RoomDatabase {
        public abstract DataAccessObject dataAccessObject();

}
