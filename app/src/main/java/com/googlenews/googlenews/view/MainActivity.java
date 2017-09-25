package com.googlenews.googlenews.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.googlenews.googlenews.R;
import com.googlenews.googlenews.adapter.NewsAdapter;
import com.googlenews.googlenews.model.Article;
import com.googlenews.googlenews.presenter.NewsFetchPresenter;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NewsInterface {
    ProgressDialog progressDialog;
    @BindView(R.id.recylerViewNews)
    RecyclerView recylerViewNews;
    ArrayList<Article> newsList;
    NewsAdapter adapter;
    @BindView(R.id.buttonSports)
    AppCompatButton buttonSports;
    @BindView(R.id.buttonNational)
    AppCompatButton buttonNational;
    @BindView(R.id.buttonPolitics)
    AppCompatButton buttonPolitics;
    @BindView(R.id.buttonInternational)
    AppCompatButton buttonInternational;
    NewsFetchPresenter presenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RequestParams requestParams = new RequestParams();
        requestParams.add("source", "abc-news-au");
        requestParams.add("sortBy", "top");
        requestParams.add("apiKey", getString(R.string.api_key));
        mProgressDialog= new ProgressDialog(this);
        presenter= new NewsFetchPresenter(this, this);
        presenter.getNews(getString(R.string.get_url), requestParams);
        newsList = new ArrayList<>();
        recylerViewNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void newsResponceFromServer(ArrayList<Article> arrayList) {
        this.newsList = arrayList;
        adapter = new NewsAdapter(this, this, newsList);
        recylerViewNews.setAdapter(adapter);
        //recylerViewNews.setAdapter(bankAdapter);
    }

    @Override
    public void startProgressDialog() {
        mProgressDialog.setMessage("Loading Wait...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void getCallToNews(int adapterPosition) {
      //  NewsFragment fragment = new NewsFragment(this, newsList.get(adapterPosition));
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, NewsFragment.newInstance(this,newsList.get(adapterPosition))).addToBackStack(null).commit();
    }


    @OnClick({R.id.buttonSports, R.id.buttonNational, R.id.buttonPolitics, R.id.buttonInternational})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonSports:
                presenter.getNews(getString(R.string.get_url), setREquestParameter("bbc-sport"));
                break;
            case R.id.buttonNational:
                presenter.getNews(getString(R.string.get_url), setREquestParameter("buzzfeed"));
                break;
            case R.id.buttonPolitics:
                presenter.getNews(getString(R.string.get_url), setREquestParameter("breitbart-news"));
                break;
            case R.id.buttonInternational:
                presenter.getNews(getString(R.string.get_url), setREquestParameter("business-insider-uk"));
                break;
        }
    }
    public RequestParams  setREquestParameter(String type){
        RequestParams requestParams = new RequestParams();
        requestParams.add("source", type);
        requestParams.add("sortBy", "top");
        requestParams.add("apiKey", getString(R.string.api_key));
        return requestParams;
    }
}
