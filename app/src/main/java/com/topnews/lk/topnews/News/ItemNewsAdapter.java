package com.topnews.lk.topnews.News;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.print.PrintAttributes;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.topnews.lk.topnews.ADetailActivity;
import com.topnews.lk.topnews.Bean.NewsBean;
import com.topnews.lk.topnews.R;
import com.topnews.lk.topnews.untils.Resolution;

import java.util.ArrayList;
import java.util.List;


/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 17 48
 */
public class ItemNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<NewsBean.Bean> objects = new ArrayList<NewsBean.Bean>();
    private Context context;
    public ItemNewsAdapter(Context context){
        this.context = context;
    }
    public void setData(List<NewsBean.Bean> objects) {
        this.objects = objects;
    }

    public void addData(List<NewsBean.Bean> newobjects){
        objects.addAll(newobjects);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_news, viewGroup, false);
            return new ItemNewsHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.footer, viewGroup, false);
            return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemNewsHolder) {
            final NewsBean.Bean bean = objects.get(position);
            if (bean == null) {
                return;
            }
            int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            int width = widthPixels;
            int height = Resolution.dipToPx(context, 200);
             //Glide 4.8 用法
            RequestOptions requestOptions = new RequestOptions()
//                    .placeholder(R.drawable.loads)
//                    .error(R.drawable.img_error)
                    .override(width, height)
                    .centerCrop();

            Glide.with(context)
                    .load(bean.getImgsrc())
                    .apply(requestOptions)
                    .into(((ItemNewsHolder) viewHolder).ivNewsImg);

            if (position == 0) {
                ((ItemNewsHolder) viewHolder).tvNewsDigest.setVisibility(View.GONE);
                ((ItemNewsHolder) viewHolder).tvNewsTitle.setText(String.format("图片：%s", bean.getTitle()));
            } else {
                ((ItemNewsHolder) viewHolder).tvNewsTitle.setText(bean.getTitle());
                ((ItemNewsHolder) viewHolder).tvNewsDigest.setText(String.format("%s:%s", bean.getMtime(), bean.getDigest()));
                ((ItemNewsHolder) viewHolder).cvNews.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ADetailActivity.class);
                        intent.putExtra("url", bean.getUrl());
                        intent.putExtra("title", bean.getTitle());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return objects.size();
    }

    protected class ItemNewsHolder extends RecyclerView.ViewHolder{
        private ImageView ivNewsImg;
        private TextView tvNewsTitle;
        private TextView tvNewsDigest;
        private CardView cvNews;

        public ItemNewsHolder(View view){
            super(view);
            ivNewsImg = (ImageView) view.findViewById(R.id.iv_news_img);
            tvNewsTitle = (TextView) view.findViewById(R.id.tv_news_title);
            tvNewsDigest = (TextView) view.findViewById(R.id.tv_news_digest);
            cvNews = (CardView) view.findViewById(R.id.cv_news);
        }
    }
        protected class FooterHolder extends RecyclerView.ViewHolder {

            public FooterHolder(View itemView) {
                super(itemView);
            }
        }
}
