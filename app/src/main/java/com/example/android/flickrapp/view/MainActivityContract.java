package com.example.android.flickrapp.view;

import com.example.android.flickrapp.view.adapter.MainActivityRvAdapter;

public interface MainActivityContract {
    void onAdapterReady(MainActivityRvAdapter adapter, int page, String totalPages);
}
