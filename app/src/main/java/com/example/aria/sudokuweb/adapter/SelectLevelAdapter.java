package com.example.aria.sudokuweb.adapter;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;

import java.util.List;

/**
 * Created by Aria on 2016/12/3.
 */

public class SelectLevelAdapter extends PagerAdapter{

    private List<View> pageList;

    public SelectLevelAdapter(List<View> pageList){
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
