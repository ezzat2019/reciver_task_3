package com.talabto.reciverappbyezzat.database.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.talabto.reciverappbyezzat.database.dao.ModelDao;
import com.talabto.reciverappbyezzat.database.model.ResponseModel;

@Database(entities = {ResponseModel.class}, version = 1, exportSchema = false)
public abstract class MainDataBase extends RoomDatabase {

    public abstract ModelDao getDao();

    private static MainDataBase mainDataBase = null;

    public static synchronized MainDataBase getInstance(Context context) {
        if (mainDataBase == null) {
            return Room.databaseBuilder(context, MainDataBase.class, "test1")
                    .allowMainThreadQueries().build();
        }
        return mainDataBase;
    }
}
