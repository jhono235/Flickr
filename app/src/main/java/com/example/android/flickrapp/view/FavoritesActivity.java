package com.example.android.flickrapp.view;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.android.flickrapp.R;
import com.example.android.flickrapp.databinding.ActivityFavoritesBinding;
import com.example.android.flickrapp.model.StringConstants;
import com.example.android.flickrapp.model.room.FavoriteDatabase;
import com.example.android.flickrapp.model.room.FavoriteList;
import com.example.android.flickrapp.view.adapter.FavoritesActivityRvAdapter;
import com.example.android.flickrapp.viewmodel.FavoritesViewModel;


import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private ActivityFavoritesBinding binding = null;
    private RecyclerView favoritesRv = null;
    private FavoritesActivityRvAdapter favoriteRvAdapter = null;
    private int spanCount = 3;
    private FavoritesViewModel mFavoritesViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFavoritesBinding.inflate(
                getLayoutInflater()
        );

        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.toolbar));
        initViews();
        getData();

    }

    private void initViews(){
        favoritesRv = findViewById(R.id.rv_favorites_list);
        favoritesRv.setLayoutManager(new GridLayoutManager(this,spanCount));
    }

    private void getData(){
        FullSizeImageActivity.favoriteDatabase = Room.databaseBuilder(
                getApplicationContext(),
                FavoriteDatabase.class,
                StringConstants.databaseName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


         List<FavoriteList> favoriteLists = FullSizeImageActivity.favoriteDatabase
                .favoriteDao().getFavoriteData();

         favoriteRvAdapter = new FavoritesActivityRvAdapter(favoriteLists, getApplicationContext());
         favoritesRv.setAdapter(favoriteRvAdapter);



    }
}
