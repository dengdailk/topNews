package com.topnews.lk.topnews;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.topnews.lk.topnews.Movie.MovieFragment;
import com.topnews.lk.topnews.News.FgNewsFragment;
import com.topnews.lk.topnews.Video.FgVideoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    Unbinder unbinder;
    @BindView(R.id.view_status)
     View view_status;
    @BindView(R.id.iv_title_news)
    ImageView iv_title_news;
    @BindView(R.id.iv_title_movie)
    ImageView iv_title_movie;
    @BindView(R.id.iv_title_video)
    ImageView iv_title_video;
    @BindView(R.id.vp_content)
    ViewPager vp_content;
    @BindView(R.id.toolbars)
    Toolbar toolbars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        getWindow().setStatusBarColor(Color.parseColor("#ffce3d3a"));
        initView();
        initContentFragment();
    }



    private void initView() {
        iv_title_news.setOnClickListener(this);
        iv_title_movie.setOnClickListener(this);
        iv_title_video.setOnClickListener(this);

    }
    private void initContentFragment() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FgNewsFragment());
        fragmentList.add(new MovieFragment());
        fragmentList.add(new FgVideoFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);

        vp_content.setAdapter(adapter);
        vp_content.setOffscreenPageLimit(2);
        vp_content.addOnPageChangeListener(this);

        setSupportActionBar(toolbars);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }

        setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_title_news:
                if(vp_content.getCurrentItem() != 0){
                    setCurrentItem(0);
                }
                break;
            case R.id.iv_title_movie:
                if(vp_content.getCurrentItem() != 1){
                    setCurrentItem(1);
                }
                break;
            case R.id.iv_title_video:
                if(vp_content.getCurrentItem() != 2){
                    setCurrentItem(2);
                }
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {
     setCurrentItem(i);
    }

    private void setCurrentItem(int i) {
        vp_content.setCurrentItem(i);
        iv_title_news.setSelected(false);
        iv_title_movie.setSelected(false);
        iv_title_video.setSelected(false);
        switch (i){
            case 0:iv_title_news.setSelected(true);
            break;
            case 1:iv_title_movie.setSelected(true);
            break;
            case 2:iv_title_video.setSelected(true);
            break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
