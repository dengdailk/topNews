package com.topnews.lk.topnews.News;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topnews.lk.topnews.FragmentAdapter;
import com.topnews.lk.topnews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FgNewsFragment extends Fragment {

    Unbinder unbinder;
    public static final int NEWS_TYPE_TOP=0;
    public static final int NEWS_TYPE_NBA=1;
    public static final int NEWS_TYPE_JOKES=2;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();

    @BindView(R.id.tl_news)
    TabLayout tl_news;
    @BindView(R.id.vp_news)
    ViewPager vp_news;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fg_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        setViewPager();

        vp_news.setOffscreenPageLimit(2);
        tl_news.setupWithViewPager(vp_news);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setViewPager() {
        fragments.add(FgNewsListFragment.newInatance(NEWS_TYPE_TOP));
        fragments.add(FgNewsListFragment.newInatance(NEWS_TYPE_NBA));
        fragments.add(FgNewsListFragment.newInatance(NEWS_TYPE_JOKES));

        fragmentTitles.add("头条");
        fragmentTitles.add("NBA");
        fragmentTitles.add("笑话");

        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(),
                fragments,fragmentTitles);
        vp_news.setAdapter(adapter);
    }
}
