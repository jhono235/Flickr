package com.example.android.flickrapp.view;

import android.util.Log;
import android.widget.TextView;

import com.example.android.flickrapp.model.datasource.remote.retrofit.RetrofitHelper;
import com.example.android.flickrapp.model.listResults.Response;
import com.example.android.flickrapp.view.adapter.MainActivityRvAdapter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.flickrapp.model.datasource.remote.retrofit.UrlConstants.API_KEY;

class MainActivityPresenter {

    private MainActivityContract contract;

    MainActivityPresenter(MainActivityContract contract) {
        this.contract = contract;
    }

    void getPhotos(String term, int page) {
        RetrofitHelper retrofitHelper = new RetrofitHelper();
        retrofitHelper.getSearchService()
                .getPhotos(MainActivity.method,
                        API_KEY,
                        MainActivity.format,
                        MainActivity.media,
                        MainActivity.noJson_Callback,
                        MainActivity.perPage,
                        String.valueOf(page),
                        term)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {
                    Response response;

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull Response response) {
                        this.response = response;

                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("TAG_PRESENTER_MAIN", "ERROR IN API CALL -->", e);

                    }

                    @Override
                    public void onComplete() {
                        setUpAdapter(response);


                    }
                });
    }

    public void setUpAdapter(Response response){
        MainActivityRvAdapter mainActivityRvAdapter = new MainActivityRvAdapter(response.getPhotos().getPhoto());
        contract.onAdapterReady(mainActivityRvAdapter, response.getPhotos().getPage(), response.getPhotos().getPages());



    }


}
