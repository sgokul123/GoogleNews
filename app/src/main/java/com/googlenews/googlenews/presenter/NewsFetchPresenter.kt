package com.googlenews.googlenews.presenter

import android.content.Context

import com.googlenews.googlenews.interactore.NewsFetchInteractore
import com.googlenews.googlenews.model.Article
import com.googlenews.googlenews.view.NewsInterface
import org.json.JSONArray

import org.json.JSONObject


class NewsFetchPresenter(var mNewsInterface: NewsInterface, var mContext: Context) : PresenterInterface {
    override fun setResponse(response: JSONObject?) {
        var articaleArray= ArrayList<Article>();
        var jsonArray: JSONArray
        jsonArray = response?.get("articles") as JSONArray
        for (i in 0..jsonArray.length() - 1) {
            var jsonobj: JSONObject
            jsonobj = jsonArray[i] as JSONObject
            var article = Article(
                    author ="",//+ jsonobj.get("author") as String,
                    description = "",//jsonobj.get("description") as String,
                    publishedAt ="",// jsonobj.get("publishedAt") as String,
                    title = jsonobj.get("title") as String,
                    urlToImage = jsonobj.get("urlToImage") as String,
                    url = jsonobj.get("url") as String)
            print(article)
            articaleArray.add(article)
        }


        mNewsInterface.newsResponceFromServer(articaleArray)
    }


    internal var service: NewsFetchInteractore

    init {
        service = NewsFetchInteractore(this, mContext)
    }

    override fun getNews(url: String) {

        service.getNewsFormGoogle(url)

    }

    override fun closeProgressDialog() {
        mNewsInterface.closeProgressDialog()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startProgressDialog() {
        mNewsInterface.startProgressDialog()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
