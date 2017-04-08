package com.example.aria.sudokuweb.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.aria.sudokuweb.model.RankInfo;

import java.util.List;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankViewPagerAdapter extends PagerAdapter{

    private List<View> pageList;

    public RankViewPagerAdapter(List<View> pageList){
        this.pageList = pageList;
    }

    @Override
    public int getCount() {
        return pageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pageList.get(position));
        return pageList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pageList.get(position));
    }
}
