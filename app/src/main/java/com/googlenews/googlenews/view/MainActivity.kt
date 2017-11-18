package com.googlenews.googlenews.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.googlenews.googlenews.R
import com.googlenews.googlenews.adapter.NewsAdapter
import com.googlenews.googlenews.model.Article
import com.googlenews.googlenews.presenter.NewsFetchPresenter

import java.util.ArrayList


class MainActivity : AppCompatActivity(), NewsInterface, View.OnClickListener {

    internal var progressDialog: ProgressDialog? = null
    lateinit var recylerViewNews :RecyclerView
     lateinit var adapter: NewsAdapter
    var newsList =ArrayList<Article>()
    lateinit var buttonSports :AppCompatButton
    lateinit var buttonNational :AppCompatButton
    lateinit var buttonInternational :AppCompatButton
    lateinit var buttonPolitics :AppCompatButton

     lateinit var presenter: NewsFetchPresenter
     var mProgressDialog: ProgressDialog? = null
    var url ="&sortBy=top&apiKey=6e52db0dbbc04f888b2dbe9a3c801a49"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressDialog = ProgressDialog(this)
        buttonSports= findViewById(R.id.buttonSports) as AppCompatButton
        buttonNational= findViewById(R.id.buttonNational) as AppCompatButton
        buttonPolitics= findViewById(R.id.buttonPolitics) as AppCompatButton
        buttonInternational= findViewById(R.id.buttonInternational) as AppCompatButton
        buttonSports= findViewById(R.id.buttonSports) as AppCompatButton
        recylerViewNews = findViewById(R.id.recylerViewNews) as RecyclerView
        presenter = NewsFetchPresenter(this, this)
        presenter.getNews(getString(R.string.get_url)+"abc-news-au"+url)

        recylerViewNews.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        buttonSports.setOnClickListener(this)
        buttonNational.setOnClickListener(this)
        buttonInternational.setOnClickListener(this)
        buttonPolitics.setOnClickListener(this)
    }

    override fun newsResponceFromServer(arrayList: ArrayList<Article>) {
        this.newsList = arrayList
        adapter = NewsAdapter(this, this, newsList)
        recylerViewNews!!.adapter = adapter
    }

    override fun startProgressDialog() {
        mProgressDialog!!.setMessage("Loading Wait...")
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog!!.show()
        mProgressDialog!!.setCancelable(false)
    }

    override fun closeProgressDialog() {
        mProgressDialog!!.dismiss()
    }

    override fun getCallToNews(adapterPosition: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,NewsFragment.newInstance(this, newsList[adapterPosition])).addToBackStack(null).commit();
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.buttonSports -> {
                presenter.getNews(setREquestParameter("bbc-sport"))
            }
            R.id.buttonNational -> {
                presenter.getNews( setREquestParameter("buzzfeed"))
            }
            R.id.buttonPolitics -> {
                presenter.getNews(setREquestParameter("breitbart-news"))
            }
            R.id.buttonInternational -> {
                presenter.getNews( setREquestParameter("business-insider-uk"))
            }
        }

    }

    fun setREquestParameter(type: String): String {
        var baseUrl=getString(R.string.get_url)+type+url
        return baseUrl
    }

}
