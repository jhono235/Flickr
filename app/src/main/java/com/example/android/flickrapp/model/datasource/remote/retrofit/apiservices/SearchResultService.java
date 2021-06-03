package com.example.android.flickrapp.model.datasource.remote.retrofit.apiservices;

import com.example.android.flickrapp.model.listResults.Photos;
import com.example.android.flickrapp.model.listResults.Response;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_API_KEY;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_FORMAT;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_MEDIA;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_METHOD;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_NO_JSON_CALLBACK;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_PAGE;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_PER_PAGE;
import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.QUERY_TEXT;

public interface SearchResultService {
@GET("rest")
Observable<Response> getPhotos(
            @Query(QUERY_METHOD) String method,
            @Query(QUERY_API_KEY) String key,
            @Query(QUERY_FORMAT) String format,
            @Query(QUERY_MEDIA) String media,
            @Query(QUERY_NO_JSON_CALLBACK) String noJson_Callback,
            @Query(QUERY_PER_PAGE) String perPage,
            @Query(QUERY_PAGE) String page,
            @Query(QUERY_TEXT) String text);


}
