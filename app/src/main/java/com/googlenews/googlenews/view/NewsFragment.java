package com.googlenews.googlenews.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.googlenews.googlenews.R;
import com.googlenews.googlenews.model.Article;

public class NewsFragment extends Fragment {
   static Article mArticle;
   static Context mContext;

    public NewsFragment() {
    }

   /* private NewsFragment(Context context, Article article) {
        this.mContext=context;
        this.mArticle=article;
        // Required empty public constructor
    }*/

    public static NewsFragment newInstance(Context context, Article article) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        mContext=context;
        mArticle=article;
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news, container, false);
        WebView mywebview = (WebView)view. findViewById(R.id.webView);
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.getSettings().setLoadsImagesAutomatically(true);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.loadUrl(mArticle.getUrl());
        return view;
    }
}
