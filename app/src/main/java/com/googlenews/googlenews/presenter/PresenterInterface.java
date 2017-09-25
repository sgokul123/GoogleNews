package com.googlenews.googlenews.presenter;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by bridgeit on 21/9/17.
 */

public interface PresenterInterface {
    public  void getNews(String url,RequestParams requestParams);
    public  void startProgressDialog();
    public  void closeProgressDialog();
    public  void newsResponce(byte[] responseBody) throws IOException;
}
