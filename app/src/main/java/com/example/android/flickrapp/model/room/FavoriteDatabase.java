package com.example.android.flickrapp.model.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.flickrapp.model.StringConstants;

@Database(entities={FavoriteList.class},version = 2)

public abstract class FavoriteDatabase extends RoomDatabase {
    public static FavoriteDatabase INSTANCE;

    public abstract FavoriteDao favoriteDao();

    public static FavoriteDatabase getDataBase(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class
            ,StringConstants.databaseName)
                    .build();
        }

        return  INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
