package com.googlenews.googlenews.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.googlenews.googlenews.R
import com.googlenews.googlenews.model.Article
import com.googlenews.googlenews.view.MainActivity
import com.googlenews.googlenews.view.NewsFragment

import java.net.URL
import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

class NewsAdapter(internal var mainActivity: MainActivity, internal var mContext: Context, internal var mNewsList: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    internal var imageViewLogo: AppCompatImageView
    private val imageUrl: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        setProfileImage(mNewsList[position].urlToImage)
        holder.textViewTitle.text = mNewsList[position].title
        // holder.textViewDiscription.setText(mNewsList.get(position).getDescription());
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    fun setProfileImage(url: String) {

        Glide.with(mContext.applicationContext)
                .load(url)
                .asBitmap()
                .into<>(object : BitmapImageViewTarget(imageViewLogo) {
                    override fun setResource(resource: Bitmap) {
                        val resized = Bitmap.createScaledBitmap(resource, 100, 100, true)
                        // Bitmap conv_bm = getRoundedRectBitmap(resized);*/
                        super.setResource(resized)
                    }
                })
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var textViewTitle: AppCompatTextView
        internal var textViewDiscription: AppCompatTextView? = null

        init {
            textViewTitle = itemView.findViewById<AppCompatTextView>(R.id.textViewTitle)
            imageViewLogo = itemView.findViewById<AppCompatImageView>(R.id.imageViewLogo)
            imageViewLogo.setOnClickListener(this)
            textViewTitle.setOnClickListener(this)
            /* textViewDiscription.setOnClickListener(this);
            textViewDiscription=itemView.findViewById(R.id.textViewDiscription);*/
        }

        override fun onClick(view: View) {
            mainActivity.getCallToNews(adapterPosition)
        }
    }
}
