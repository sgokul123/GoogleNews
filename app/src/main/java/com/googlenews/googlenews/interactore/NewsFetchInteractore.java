package com.googlenews.googlenews.interactore;

import android.content.Context;
import android.util.Log;

import com.googlenews.googlenews.presenter.PresenterInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;


public class NewsFetchInteractore  {
    Context mContext;
    PresenterInterface mPresenterInterface;
    public NewsFetchInteractore(PresenterInterface presenterInterface, Context context) {
        this.mPresenterInterface=presenterInterface;
        this.mContext=context;
    }

    public  void getNewsFormGoogle(String url ,RequestParams requestParams){

        mPresenterInterface.startProgressDialog();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    mPresenterInterface.newsResponce(responseBody);
                    mPresenterInterface.closeProgressDialog();
                }  catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("hrController", "onSuccess: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);

            }
        });

    }
}
