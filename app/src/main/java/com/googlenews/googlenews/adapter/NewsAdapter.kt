package com.googlenews.googlenews.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.googlenews.googlenews.R
import com.googlenews.googlenews.model.Article
import com.googlenews.googlenews.view.MainActivity

import java.util.ArrayList

import com.squareup.picasso.Picasso

class NewsAdapter(internal var mainActivity: MainActivity, internal var mContext: Context, internal var mNewsList: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        setProfileImage(mNewsList[position].urlToImage!!,holder.imageViewLogo)
        holder.textViewTitle.text = mNewsList[position].title
        // holder.textViewDiscription.setText(mNewsList.get(position).getDescription());
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    fun setProfileImage(url: String, imageViewLogo: AppCompatImageView) {

        Picasso.with(mContext).load(url).into(imageViewLogo);

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var textViewTitle: AppCompatTextView
        var imageViewLogo: AppCompatImageView

        init {
            textViewTitle = itemView.findViewById(R.id.textViewTitle)
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo)
            imageViewLogo.setOnClickListener(this)
            textViewTitle.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            mainActivity.getCallToNews(adapterPosition)
        }
    }
}
