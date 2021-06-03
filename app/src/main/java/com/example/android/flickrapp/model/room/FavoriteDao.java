package com.example.android.flickrapp.model.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Used to define your database interactions
@Dao
public interface FavoriteDao {

    @Insert
    public void addData(FavoriteList favoriteList);

   //gets contents of DB
    @Query("select * from favoritelist")
    public List<FavoriteList> getFavoriteData();

   //checks if element exists in DB
    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    public String isFavorite(String id);

    @Delete
    public void delete(FavoriteList favoriteList);
}
