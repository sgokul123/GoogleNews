package com.googlenews.googlenews.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import com.googlenews.googlenews.R
import com.googlenews.googlenews.model.Article

class NewsFragment : Fragment() {
     lateinit var mArticle: Article
     lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_news, container, false)
        var mywebview : WebView? = null;
        mywebview = view.findViewById<View>(R.id.webView) as WebView
        var url=mArticle.url
        mywebview.settings.javaScriptEnabled = true
        mywebview.settings.loadsImagesAutomatically = true
        mywebview.setBackgroundColor(0)
       mywebview.webViewClient= WebViewClient()
        mywebview.loadUrl(url.toString())
        return view
    }

    companion object {
        fun  newInstance(context: Context, article: Article): NewsFragment {
            val fragment = NewsFragment()
            fragment.setVar(context,article)
            return fragment
        }
    }

    private fun setVar(context: Context, article: Article) {
        mArticle= article
        mContext=context
    }
}
