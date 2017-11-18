package com.googlenews.googlenews.interactore

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.googlenews.googlenews.presenter.NewsFetchPresenter
import org.json.JSONObject


class NewsFetchInteractore(newsFetchPresenter: NewsFetchPresenter, mContext: Context) {
    var mcontext: Context=mContext;
    var newsFetchPresenter=newsFetchPresenter
    fun getNewsFormGoogle(url: String) {
        val que=Volley.newRequestQueue(mcontext.applicationContext)
        val req=JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener {
                            response ->
                    callResponse(response)
        },Response.ErrorListener {
        })
        que.add(req)
    }
    private fun callResponse(response: JSONObject?) {
        newsFetchPresenter.setResponse(response);

    }
}

