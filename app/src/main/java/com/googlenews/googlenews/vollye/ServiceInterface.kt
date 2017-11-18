package com.googlenews.googlenews.vollye

import com.loopj.android.http.RequestParams
import org.json.JSONObject

/**
 * Created by bridgeit on 16/11/17.
 */
interface ServiceInterface {
    fun post(path: String, params: RequestParams, completionHandler: (response: JSONObject?) -> Unit)
}