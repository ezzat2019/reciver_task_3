package com.talabto.reciverappbyezzat.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.talabto.reciverappbyezzat.database.model.ResponseModel;

import java.util.List;

@Dao
public interface ModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setNewData(ResponseModel responseModel);

    @Query("select * from model")
    List<ResponseModel> getAllData();
}
