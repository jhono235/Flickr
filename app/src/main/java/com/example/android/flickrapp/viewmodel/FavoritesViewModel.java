package com.example.android.flickrapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.flickrapp.model.room.FavoriteList;



import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private DataRepo mDataRepo;
    private LiveData<List<FavoriteList>> mListLiveData;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        mDataRepo = new DataRepo(application);
        mListLiveData = mDataRepo.getAllFavorites();

    }

    public LiveData<List<FavoriteList>> getAllData() {
        return mListLiveData;
    }

    public void insertItem(FavoriteList favoriteList) {
        mDataRepo.insert(favoriteList);
    }

    public void deleteItem(FavoriteList favoriteList) {
        mDataRepo.deleteItem(favoriteList);
    }
}
