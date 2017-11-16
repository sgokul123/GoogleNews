package com.googlenews.googlenews.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import com.googlenews.googlenews.R
import com.googlenews.googlenews.model.Article

class NewsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_news, container, false)
        val mywebview = view.findViewById<View>(R.id.webView) as WebView
        val webSettings = mywebview.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        mywebview.webViewClient = WebViewClient()
        mywebview.settings.loadsImagesAutomatically = true
        mywebview.settings.javaScriptEnabled = true
        mywebview.loadUrl(mArticle.url)
        return view
    }

    companion object {
        internal var mArticle: Article
        internal var mContext: Context

        /* private NewsFragment(Context context, Article article) {
        this.mContext=context;
        this.mArticle=article;
        // Required empty public constructor
    }*/

        fun newInstance(context: Context, article: Article): NewsFragment {
            val fragment = NewsFragment()
            val args = Bundle()
            mContext = context
            mArticle = article
            fragment.arguments = args
            return fragment
        }
    }
}
