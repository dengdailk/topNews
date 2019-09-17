package com.topnews.lk.topnews;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * File description
 *
 * @author lk
 * @date 2018/12/21 09 16
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();


    public FragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    public FragmentAdapter(FragmentManager fm,List<Fragment> fragments,List<String> fragmentTitles) {
        super(fm);
        this.fragments = fragments;
        this.fragmentTitles = fragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(fragmentTitles != null) {
            return fragmentTitles.get(position);
        }else{
            return "";
        }
    }
}
