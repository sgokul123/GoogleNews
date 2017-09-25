package com.googlenews.googlenews.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.googlenews.googlenews.R;
import com.googlenews.googlenews.model.Article;
import com.googlenews.googlenews.view.MainActivity;
import com.googlenews.googlenews.view.NewsFragment;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Article> mNewsList;

    AppCompatImageView imageViewLogo;
    private String imageUrl;
    MainActivity mainActivity;
    public NewsAdapter(MainActivity mainActivity, Context context, ArrayList<Article> newsList) {
        this.mNewsList = newsList;
        this.mContext=context;
        this.mainActivity=mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        setProfileImage(mNewsList.get(position).getUrlToImage());
        holder.textViewTitle.setText(mNewsList.get(position).getTitle());
       // holder.textViewDiscription.setText(mNewsList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setProfileImage(String url) {

        Glide.with(mContext.getApplicationContext())
                .load(url)
                .asBitmap()
                .into(new BitmapImageViewTarget(imageViewLogo) {
                    @Override
                    protected void setResource(Bitmap resource) {
                       Bitmap resized = Bitmap.createScaledBitmap(resource, 100, 100, true);
                       // Bitmap conv_bm = getRoundedRectBitmap(resized);*/
                        super.setResource(resized);
                    }
                });
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatTextView textViewTitle;
        AppCompatTextView textViewDiscription;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            imageViewLogo=itemView.findViewById(R.id.imageViewLogo);
            imageViewLogo.setOnClickListener(this);
            textViewTitle.setOnClickListener(this);
           /* textViewDiscription.setOnClickListener(this);
            textViewDiscription=itemView.findViewById(R.id.textViewDiscription);*/
        }

        @Override
        public void onClick(View view) {
            mainActivity.getCallToNews(getAdapterPosition());
        }
    }
}
