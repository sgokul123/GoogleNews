package com.googlenews.googlenews.view;

import com.googlenews.googlenews.model.Article;

import java.util.ArrayList;

/**
 * Created by bridgeit on 21/9/17.
 */

public interface NewsInterface {
    public void newsResponceFromServer(ArrayList<Article> arrayList);
    public  void startProgressDialog();
    public  void closeProgressDialog();
    void getCallToNews(int adapterPosition);
}
