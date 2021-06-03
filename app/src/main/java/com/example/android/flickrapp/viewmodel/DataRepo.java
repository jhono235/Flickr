package com.example.android.flickrapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android.flickrapp.model.room.FavoriteDao;
import com.example.android.flickrapp.model.room.FavoriteDatabase;
import com.example.android.flickrapp.model.room.FavoriteList;

import java.util.List;

public class DataRepo {
    private FavoriteDao mFavoritesDao;
    private LiveData<List<FavoriteList>> mAllFavs;

    public DataRepo(Application application) {
        FavoriteDatabase favoriteDatabase = FavoriteDatabase.getDataBase(application);
        this.mFavoritesDao = favoriteDatabase.favoriteDao();
        //this.mAllFavs = mFavoritesDao.getFavoriteData();
    }

    LiveData<List<FavoriteList>> getAllFavorites() {
        return mAllFavs;
    }

    //calling db operations from non-ui thread
    public void insert(FavoriteList favoriteList) {
        new insertAsyncTask(mFavoritesDao).execute(favoriteList);
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteList,Void,Void> {
        private FavoriteDao mAsyncTaskDao;
        insertAsyncTask(FavoriteDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteList... favoriteLists) {
            mAsyncTaskDao.addData(favoriteLists[0]);
            return null;
        }
    }

    public void deleteItem(FavoriteList favoriteList) {
        new deleteAsyncTask(mFavoritesDao).execute(favoriteList);
    }

    private static class deleteAsyncTask extends AsyncTask<FavoriteList,Void,Void> {
        private FavoriteDao mAsyncTaskDao;
        deleteAsyncTask(FavoriteDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteList... favoriteLists) {
            mAsyncTaskDao.delete(favoriteLists[0]);
            return null;
        }
    }




}
