package com.talabto.reciverappbyezzat.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "model")
public class ResponseModel {
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "data")
    String data;

    public ResponseModel(int id, String data) {
        this.id = id;
        this.data = data;
    }
@Ignore
    public ResponseModel(String data) {
        this.data = data;
    }

    public ResponseModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
