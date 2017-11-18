package com.googlenews.googlenews.presenter

import org.json.JSONObject


interface PresenterInterface {
    fun getNews(url: String)
    fun startProgressDialog()
    fun closeProgressDialog()
    fun setResponse(response: JSONObject?)

}
