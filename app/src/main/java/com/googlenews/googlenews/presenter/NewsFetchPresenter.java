package com.googlenews.googlenews.presenter;

import android.content.Context;
import android.util.Log;

import com.googlenews.googlenews.interactore.NewsFetchInteractore;
import com.googlenews.googlenews.model.Article;
import com.googlenews.googlenews.view.NewsInterface;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class NewsFetchPresenter implements  PresenterInterface{
    ArrayList<Article> arrayList;
    NewsInterface mNewsInterface;
    Context mContext;
    NewsFetchInteractore interactore;
    public NewsFetchPresenter(NewsInterface newsInterface, Context context) {
        this.mContext=context;
        this.mNewsInterface=newsInterface;
         interactore=new NewsFetchInteractore(this,mContext);
    }

    @Override
    public void getNews(String url,RequestParams requestParams) {
        interactore.getNewsFormGoogle(url,requestParams);
    }

    @Override
    public void startProgressDialog() {
        mNewsInterface.startProgressDialog();
    }

    @Override
    public void closeProgressDialog() {
        mNewsInterface.closeProgressDialog();
    }

    @Override
    public void newsResponce(byte[] responseBody) throws IOException {
        JSONObject object= null;
        JSONArray jsonArray;
        arrayList=new ArrayList<>();
        try {

            object = new JSONObject(new String(responseBody));
            jsonArray=object.getJSONArray("articles");
            Log.i("ghfg", "newsResponce: "+jsonArray.length());

            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject jpersonObj = jsonArray.getJSONObject(i);
                Article article = new Article();
                article.setUrl(jpersonObj.getString("url"));
                article.setAuthor(jpersonObj.getString("author"));
                article.setDescription(jpersonObj.getString("description"));
                article.setTitle(jpersonObj.getString("title"));
                article.setUrlToImage(jpersonObj.getString("urlToImage"));
                article.setPublishedAt(jpersonObj.getString("publishedAt"));
                arrayList.add(article);
            }

            mNewsInterface.newsResponceFromServer(arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
