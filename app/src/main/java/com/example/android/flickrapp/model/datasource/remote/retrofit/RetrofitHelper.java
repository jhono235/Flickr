package com.example.android.flickrapp.model.datasource.remote.retrofit;

import com.example.android.flickrapp.model.datasource.remote.retrofit.apiservices.SearchResultService;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.BASE_URL;

public class RetrofitHelper {



    private Retrofit getRetrofitInstance(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public SearchResultService getSearchService(){
        return getRetrofitInstance()
                .create(SearchResultService.class);
    }

}
